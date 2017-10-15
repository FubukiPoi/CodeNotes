
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