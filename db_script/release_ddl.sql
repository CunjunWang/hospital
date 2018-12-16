SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP Database if exists hospital_emp;
CREATE Database hospital_emp;
USE hospital_emp;
alter database hospital_emp character set 'utf8mb4';

create table if not exists h_staff (
  ID                   bigint not null AUTO_INCREMENT comment '主键',
  uuid                 varchar(200) comment '用户唯一标识',
  company_id           bigint comment '所属公司ID',
  company_name         varchar(200) comment '所属公司名称',
  hospital             varchar(200) comment '所属医院',
  role_id              bigint comment '角色表ID',
  role_code            varchar(200) comment '角色代码',
  role_name            varchar(200) comment '角色名称',
  level_id             bigint comment '能级ID',
  level_name           varchar(200) comment '能级名称',
  emp_name             varchar(200) comment '姓名',
  identity             varchar(200) comment '身份证号',
  emp_no               varchar(200) comment '工号',
  mobile               bigint comment '手机号',
  is_onjob             int comment '是否在职，0在职，1离职',
  on_job_date          date comment '入职日期',
  off_job_date         date comment '离职日期',
  remark               varchar(200) comment '备注',
  is_del               int comment '0有效，1失效',
  create_date          datetime,
  create_by            bigint,
  update_date          datetime,
  update_by            bigint,
  primary key (ID)
);

create table if not exists h_staff_level
(
  ID                   bigint not null AUTO_INCREMENT comment '主键',
  level_code           varchar(200) comment '等级代码',
  level_name           varchar(200) comment '能级名称',
  remark               varchar(200) comment '备注',
  is_del               int comment '逻辑删除标识，0有效，1无效',
  primary key (ID)
);

create table if not exists h_staff_role
(
  ID                   bigint not null AUTO_INCREMENT comment '主键',
  role_code            varchar(200) comment 'DOCTOR, NURSE等',
  role_name            varchar(200) comment '角色名称(医生、护士等)',
  remark               varchar(200) comment '备注',
  is_del               int comment '逻辑删除标识，0有效，1无效',
  primary key (ID)
);

create table if not exists h_staff_attachment
(
  ID                   bigint not null AUTO_INCREMENT comment '主键',
  emp_id               varchar(200) comment '员工ID',
  attach_url           varchar(200) comment '附件地址',
  attach_type          int comment '附件类型,身份证/保密协议/证件照/头像(见主数据表)',
  attach_type_name     varchar(200) comment '附件类型名称',
  is_del               int comment '逻辑删除标识，0有效，1无效',
  create_date          datetime comment '上传时间',
  create_by            bigint comment '上传人',
  update_date          datetime comment '更新时间',
  update_by            bigint comment '更新人',
  primary key (ID)
);

create table if not exists h_staff_level_history
(
  ID                   bigint not null AUTO_INCREMENT,
  emp_id               bigint comment '员工ID',
  emp_name             varchar(200) comment '员工姓名',
  level_id             bigint comment '级别ID',
  level_name           varchar(200) comment '级别名称',
  start_date           date comment '开始日期',
  end_date             date comment '结束日期',
  status               varchar(200) comment '状态(见元数据表1001类型)',
  is_del               int comment '逻辑删除标识',
  create_by            bigint comment '创建人',
  create_date          datetime comment '创建时间',
  update_by            bigint comment '更新人',
  update_date          datetime comment '更新时间',
  primary key (ID)
);