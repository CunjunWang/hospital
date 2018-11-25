-- auto-generated definition
create table h_patient
(
  id                     bigint auto_increment
  comment '主键'
    primary key,
  patient_name           varchar(200)                       null
  comment '病人姓名',
  patient_gender         int                                null
  comment '病人性别',
  patient_age            int                                null
  comment '病人年龄',
  patient_account_number bigint                             null
  comment '病人账号',
  is_del                 tinyint default '0'                null
  comment '逻辑删除标识符 0-未删除 1-已删除',
  patient_status         int                                null
  comment '病人状态',
  create_time            datetime default CURRENT_TIMESTAMP null
  comment '创建时间',
  create_by              bigint                             null
  comment '创建人账号Id',
  update_time            datetime default CURRENT_TIMESTAMP null
  comment '更新时间',
  update_by              bigint                             null
  comment '更新人账号Id',
  patient_uuid           varchar(200)                       null
  comment '病人UUID'
)
  comment '病人表';