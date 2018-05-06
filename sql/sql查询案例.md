
# MySQL常见的三种存储引擎（InnoDB、MyISAM、MEMORY）
1. InnoDB：支持事务处理，支持外键，支持崩溃修复能力和并发控制。如果需要对事务的完整性要求比较高（比如银行），要求实现并发控制（比如售票），那选择InnoDB有很大的优势。如果需要频繁的更新、删除操作的数据库，也可以选择InnoDB，因为支持事务的提交（commit）和回滚（rollback）。 

2. MyISAM：插入数据快，空间和内存使用比较低。如果表主要是用于插入新记录和读出记录，那么选择MyISAM能实现处理高效率。如果应用的完整性、并发性要求比 较低，也可以使用。

3. MEMORY：所有的数据都在内存中，数据的处理速度快，但是安全性不高。如果需要很快的读写速度，对数据的安全性要求较低，可以选择MEMOEY。它对表的大小有要求，不能建立太大的表。所以，这类数据库只使用在相对较小的数据库表。

## 1. 分别取出字段ERROR_PARAM的   6;-66;666  的值
```
SUBSTR(CL.ERROR_PARAM,1,
    INSTR(CL.ERROR_PARAM,';',1)-1) AS PRI_BALANCE_ADJ,
SUBSTR(CL.ERROR_PARAM,  \
    INSTR(CL.ERROR_PARAM, ';', 1, 1) + 1,
    INSTR(CL.ERROR_PARAM, ';', 1, 2) -
    INSTR(CL.ERROR_PARAM, ';', 1, 1) - 1) AS ACC_INTEREST_ADJ,
SUBSTR(CL.ERROR_PARAM,
    INSTR(CL.ERROR_PARAM, ';', 1, 2) + 1) AS DELTA_CHANGE
```
***

## 2. NVL()
```
NVL(expr1,expr2); //当expr1值为null,使用expr2代替
NVL2(comm,sal+commn,sal);//commn!=null,use (sal+commn),commn==null use (sal)
```
***

## 3. CAST(field,转换类型)
```
CAST(ERROR_PARAM, number(29,10));
```

## 4.根据字段查询表
查询所有使用NUMBER(X),NUMBER(X.XX)的表
```
SELECT distinct
       a.table_name as TABLE_NAME,
       b.column_name as COLUMN_NAME,
       concat(b.data_type,concat('(',concat(b.DATA_PRECISION,concat('.',concat(b.DATA_SCALE,')')))))
FROM user_col_comments a
    ,all_tab_columns b
WHERE a.table_name = b.table_name and
    b.data_type =  'NUMBER' and
    b.DATA_PRECISION is not null
    order by a.table_name
```
查询VERSION,NUMBER类型,NOT NULL=Y的表
```
SELECT distinct
       a.table_name as TABLE_NAME,
       b.column_name as COLUMN_NAME,
       b.DATA_TYPE as DATA_TYPE,
       b.NULLABLE as NotNull
FROM user_col_comments a
    ,all_tab_columns b
WHERE a.table_name = b.table_name and
    b.data_type =  'NUMBER' and
    b.COLUMN_NAME='VERSION' and
    b.NULLABLE ='Y'
    order by a.table_name
```