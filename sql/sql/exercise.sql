/**
1.查询" 01 "课程比" 02 "课程成绩高的学生的信息及课程分数 
1.1 查询同时存在" 01 "课程和" 02 "课程的情况 
1.2 查询存在" 01 "课程但可能不存在" 02 "课程的情况(不存在时显示为 null ) 
1.3 查询不存在" 01 "课程但存在" 02 "课程的情况
*/
SELECT * FROM
(SELECT SC.SId, SC.score FROM SC WHERE SC.CId = '01') AS t1 INNER JOIN
(SELECT SC.SId, SC.score FROM SC WHERE SC.CId = '02') AS t2 ON t1.SId = t2.SId
WHERE t1.score > t2.score

SELECT * FROM
(SELECT SC.SId, SC.score FROM SC WHERE SC.CId = '01') AS t1 INNER JOIN
(SELECT SC.SId, SC.score FROM SC WHERE SC.CId = '02') AS t2 ON t1.SId = t2.SId

SELECT * FROM 
sc WHERE SId NOT IN (SELECT SId FROM sc WHERE CId='01') AND CId='02'
/**
2.查询平均成绩大于等于 60 分的同学的学生编号和学生姓名和平均成绩
*/
SELECT st.SId,st.Sname,AVG(s.score) FROM sc s LEFT JOIN student st ON s.SId=st.SId GROUP BY s.SId HAVING AVG(s.score)>60

SELECT t1.sid, t1.sname, t2.avgscore 
FROM student AS t1 INNER JOIN (
SELECT sc.sid, AVG(sc.score) AS avgscore FROM sc GROUP BY sc.sid 
HAVING avgscore >= 60) AS t2
ON t1.sid = t2.sid

/**
3.查询在 SC 表存在成绩的学生信息
*/
SELECT * FROM student WHERE SID IN(SELECT SId FROM sc WHERE score IS NOT NULL GROUP BY SId )

/**
4.查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩(没成绩的显示为 null ) 4.1 查有成绩的学生信息
*/
SELECT st.SId,st.Sname,s.c, s.s FROM student st LEFT JOIN (SELECT SId ,COUNT(SId) c,SUM(score) s FROM sc GROUP BY SId)s  ON st.SId=s.SId

/**
5.查询「李」姓老师的数量
*/
SELECT COUNT(1) FROM teacher WHERE Tname LIKE '李%'
/**
6.查询学过「张三」老师授课的同学的信息
*/
SELECT st.* FROM sc s,student st,teacher t WHERE s.SId=st.SId AND s.CId=t.TId AND t.Tname='张三'
/**
7.查询没有学全所有课程的同学的信息
*/
SELECT st.* FROM student st LEFT JOIN sc s ON st.SId=s.SId GROUP BY st.SId HAVING COUNT(1)<(SELECT COUNT(1)FROM teacher)
/**
8.查询至少有一门课与学号为" 01 "的同学所学相同的同学的信息
*/
SELECT DISTINCT st.* FROM student st,sc s WHERE st.SId=s.SId
AND s.CId IN 
(SELECT CId FROM sc WHERE SId='01')

/**
9.查询和" 01 "号的同学学习的课程 完全相同的其他同学的信息
*/
SELECT * FROM student WHERE SId IN 
(SELECT SId FROM sc WHERE SId NOT IN
(SELECT SId FROM sc WHERE CId NOT IN (SELECT CId FROM sc WHERE SId='01'))
GROUP BY SId 
HAVING COUNT(*)=(SELECT COUNT(*) FROM sc WHERE SId='01') AND SId != '01');
/**
10.查询没学过"张三"老师讲授的任一门课程的学生姓名
*/SELECT *
FROM student
WHERE student.SId NOT IN(SELECT
                           student.SId
                         FROM (SELECT
                                 student.SId,
                                 t.CId
                               FROM (SELECT
                                       CId
                                     FROM sc,
                                       teacher
                                     WHERE sc.CId = teacher.TId
                                         AND teacher.Tname = '张三') AS t,
                                 student) AS t1
                           RIGHT JOIN sc
                             ON t1.SId = sc.SId
                               AND t1.CId = sc.CId,
                           student
                         WHERE t1.SId = student.SId)
/**
11.查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
*/
 SELECT
   student.SId,
   student.Sname,
   t2.avgscore
 FROM student,
   (SELECT
      sc.SId,
      AVG(sc.score)  AS avgscore
    FROM sc
    GROUP BY sc.SId) AS t2
 WHERE student.SId = t2.SId
     AND student.SId IN(SELECT
                          t1.SId
                        FROM (SELECT *
                              FROM sc
                              WHERE sc.score < 60) AS t1
                        GROUP BY t1.SId
                        HAVING COUNT( * ) >= 2)
                        
/**
13.按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
*/
