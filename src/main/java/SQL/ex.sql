1.SQL中如何统计员工最大连续打卡天数
id	date	success
1	2020/4/2	1
1	2020/4/3	1
1	2020/4/4	1
1	2020/4/5	0
1	2020/4/6	1
1	2020/4/7	1
2	2020/4/2	1
2	2020/4/3	1


1)通过窗口函数对员工打卡数据进行排序
id	date		rn
1	2020/4/2	1
1	2020/4/3	2
1	2020/4/4	3
1	2020/4/6	4
1	2020/4/7	5
2	2020/4/2	1
2	2020/4/3	2

2)计算当前打卡日期与序号差值,我们可以看到如果是连续打卡,则label_date值是相同的

id	date		rn		label_date
1	2020/4/2	1	    2020/4/1
1	2020/4/3	2	    2020/4/1
1	2020/4/4	3	    2020/4/1
1	2020/4/6	4	    2020/4/2
1	2020/4/7	5	    2020/4/2
2	2020/4/2	1	    2020/4/1
2	2020/4/3	2	    2020/4/1

3)对label_date进行计数,取计数结果count_day的最大值即为最大连续打卡天数

id	label_date	count_day
1	2020/4/1	3
1	2020/4/2	2
2	2020/4/1	2

select
	c.id,max(count_day) as max_day
from
(
	select
		b.id,b.label_date,count(*) as count_day
	from
	(
		select
			a.id,a.date,date_sub(a.date, cast(rn as int)) as label_date
		from
		(
			select
				id,date,
				row_number() over(partition by id order by date) as rn
			from events where success=1
		)a
	)b
	group by b.id,b.label_date
)c
group by c.id;

========================================================================================================================

LAG
LAG(col,n,DEFAULT) 用于统计窗口内往上第n行值
参数1为列名,参数2为往上第n行(可选,默认为1),参数3为默认值(当往上第n行为NULL时候,取默认值,如不指定,则为NULL)

LEAD
与LAG相反
LEAD(col,n,DEFAULT) 用于统计窗口内往下第n行值
参数1为列名,参数2为往下第n行(可选,默认为1),参数3为默认值(当往下第n行为NULL时候,取默认值,如不指定,则为NULL)


用户Peter在浏览网页,在某个时刻,Peter点进了某个页面,过一段时间后,Peter又进入了另外一个页面,如此反复,那怎么去统计Peter在某个特定网页的停留时间呢,又或是怎么统计某个网页用户停留的总时间呢?
+------------------+----------------------+---------------+--+
| user_log.userid  |    user_log.time     | user_log.url  |
+------------------+----------------------+---------------+--+
| Peter            | 2015-10-12 01:10:00  | url1          |
| Peter            | 2015-10-12 01:15:10  | url2          |
| Peter            | 2015-10-12 01:16:40  | url3          |
| Peter            | 2015-10-12 02:13:00  | url4          |
| Peter            | 2015-10-12 03:14:30  | url5          |
| Marry            | 2015-11-12 01:10:00  | url1          |
| Marry            | 2015-11-12 01:15:10  | url2          |
| Marry            | 2015-11-12 01:16:40  | url3          |
| Marry            | 2015-11-12 02:13:00  | url4          |
| Marry            | 2015-11-12 03:14:30  | url5          |
+------------------+----------------------+---------------+--+

1.获取用户在某个页面停留的起始与结束时间
select userid,
       time stime,
       lead(time) over(partition by userid order by time) etime,
       url from test.user_log;

+---------+----------------------+----------------------+-------+--+
| userid  |        stime         |        etime         |  url  |
+---------+----------------------+----------------------+-------+--+
| Marry   | 2015-11-12 01:10:00  | 2015-11-12 01:15:10  | url1  |
| Marry   | 2015-11-12 01:15:10  | 2015-11-12 01:16:40  | url2  |
| Marry   | 2015-11-12 01:16:40  | 2015-11-12 02:13:00  | url3  |
| Marry   | 2015-11-12 02:13:00  | 2015-11-12 03:14:30  | url4  |
| Marry   | 2015-11-12 03:14:30  | NULL                 | url5  |
| Peter   | 2015-10-12 01:10:00  | 2015-10-12 01:15:10  | url1  |
| Peter   | 2015-10-12 01:15:10  | 2015-10-12 01:16:40  | url2  |
| Peter   | 2015-10-12 01:16:40  | 2015-10-12 02:13:00  | url3  |
| Peter   | 2015-10-12 02:13:00  | 2015-10-12 03:14:30  | url4  |
| Peter   | 2015-10-12 03:14:30  | NULL                 | url5  |
+---------+----------------------+----------------------+-------+--+

2.计算用户在页面停留的时间间隔(实际分析当中,这里要做数据清洗工作,如果一个用户停留了4,5个小时,那这条记录肯定是不可取的。)

select userid,
       time stime,
       lead(time) over(partition by userid order by time) etime,
       UNIX_TIMESTAMP(lead(time) over(partition by userid order by time),'yyyy-MM-dd HH:mm:ss')- UNIX_TIMESTAMP(time,'yyyy-MM-dd HH:mm:ss') period,
       url from test.user_log;

+---------+----------------------+----------------------+---------+-------+--+
| userid  |        stime         |        etime         | period  |  url  |
+---------+----------------------+----------------------+---------+-------+--+
| Marry   | 2015-11-12 01:10:00  | 2015-11-12 01:15:10  | 310     | url1  |
| Marry   | 2015-11-12 01:15:10  | 2015-11-12 01:16:40  | 90      | url2  |
| Marry   | 2015-11-12 01:16:40  | 2015-11-12 02:13:00  | 3380    | url3  |
| Marry   | 2015-11-12 02:13:00  | 2015-11-12 03:14:30  | 3690    | url4  |
| Marry   | 2015-11-12 03:14:30  | NULL                 | NULL    | url5  |
| Peter   | 2015-10-12 01:10:00  | 2015-10-12 01:15:10  | 310     | url1  |
| Peter   | 2015-10-12 01:15:10  | 2015-10-12 01:16:40  | 90      | url2  |
| Peter   | 2015-10-12 01:16:40  | 2015-10-12 02:13:00  | 3380    | url3  |
| Peter   | 2015-10-12 02:13:00  | 2015-10-12 03:14:30  | 3690    | url4  |
| Peter   | 2015-10-12 03:14:30  | NULL                 | NULL    | url5  |
+---------+----------------------+----------------------+---------+-------+--+


3.计算每个页面停留的总时间,某个用户访问某个页面的总时间

select nvl(url,'-1') url,
       nvl(userid,'-1') userid,
       sum(period) totol_peroid from (
select userid,
       time stime,
       lead(time) over(partition by userid order by time) etime,
       UNIX_TIMESTAMP(lead(time) over(partition by userid order by time),'yyyy-MM-dd HH:mm:ss')- UNIX_TIMESTAMP(time,'yyyy-MM-dd HH:mm:ss') period,
       url 
  from test.user_log
) a group by url, userid with rollup;

+-------+---------+---------------+--+
|  url  | userid  | totol_peroid  |
+-------+---------+---------------+--+
| -1    | -1      | 14940         |
| url1  | -1      | 620           |
| url1  | Marry   | 310           |
| url1  | Peter   | 310           |
| url2  | -1      | 180           |
| url2  | Marry   | 90            |
| url2  | Peter   | 90            |
| url3  | -1      | 6760          |
| url3  | Marry   | 3380          |
| url3  | Peter   | 3380          |
| url4  | -1      | 7380          |
| url4  | Marry   | 3690          |
| url4  | Peter   | 3690          |
| url5  | -1      | NULL          |
| url5  | Marry   | NULL          |
| url5  | Peter   | NULL          |
+-------+---------+---------------+--+

========================================================================================================================


name,type,scor
张三 语文 80
张三 数学 98
张三 英语 65
李四 语文 70
李四 数学 80
李四 英语 90

SELECT tname,
CASE `ttype` WHEN '语文' THEN tscor ELSE 0 END AS '语文',
CASE `ttype` WHEN '数学' THEN tscor ELSE 0 END AS '数学',
CASE `ttype` WHEN '英语' THEN tscor ELSE 0 END AS '英语'
FROM testScore

select tname,
(case when ttype='语文' then tscor else 0 end) '语文',
(case when ttype ='数学' then tscor else 0 end) '数学',
(case when ttype ='英语' then tscor else 0 end) '英语'
from testScore group by tname;

select
    tname as '姓名' ,
    max(case ttype when '语文' then tscor else 0 end) '语文',
    max(case ttype when '数学' then tscor else 0 end) '数学',
    max(case ttype when '英语' then tscor else 0 end) '英语'
from testScore
group by tname;

select