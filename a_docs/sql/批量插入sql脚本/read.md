在执行创建函数之前， 首先请保证 log_bin_trust_function_creators 参数为 1， 即 on 开启状态。
注意：
尽量一行一行的执行sql脚本

创建存储过程出现：This function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration and binary logging is enabled (you *might* want to use the less safe log_bin_trust_function_creators variable)
解决方法：
set global log_bin_trust_function_creators=TRUE;