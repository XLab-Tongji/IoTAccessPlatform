drop table if exists `sensor_msg`;
drop table if exists `sensor`;
drop table if exists `config_detail`;
drop table if exists `sensor_config`;
drop table if exists `sensor_type`;
drop table if exists `sensor_state`;
drop table if exists `user`;


create table `sensor_type`(
  `typename` varchar(20) not null comment '主键',
  primary key (`typename`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `sensor_state`(
  `state` varchar(20) not null comment '主键',
  primary key (`state`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `sensor_config` (
  `id` bigint(20) not null auto_increment comment '主键',
  `create_user` varchar(20) default null comment '创始人',
  `create_time` datetime not null comment '创造时间',
  primary key (`id`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `config_detail` (
  `id` bigint(20) not null comment '传感器配置id',
  `type` varchar(20) not null comment '传感器类型',
  `num` bigint(20) default 0 comment '该类型的数量',
  primary key (`id`, `type`),
  foreign key (`id`) references sensor_config(`id`),
  foreign key (`type`) references sensor_type(`typename`)
)ENGINE =InnoDB DEFAULT charset = utf8;


create table `sensor` (
  `id` bigint(20) not null auto_increment comment '主键',
  `config_id` bigint(20) not null comment '属于哪一个配置',
  `type` varchar(20) default null comment '传感器类型',
  `descr` varchar(200) default 'made in china' comment '传感器描述',
  `host` varchar(20) default '127.0.0.1' comment '传感器host',
  `port` varchar(10) default '8080' comment '传感器port',
  `state` varchar(20) default 'offline' comment '传感器是否在线',
  `msg` varchar(200) default null comment '该传感器的最近一条消息',
  `time` datetime default null comment '该传感器状态最近一次更新的时间',
  primary key (`id`),
  foreign key (`config_id`) references `sensor_config`(`id`),
  foreign key (`type`) references `sensor_type`(`typename`),
  foreign key (`state`) references `sensor_state`(`state`)
)ENGINE = InnoDB DEFAULT charset=utf8;


create table `sensor_msg` (
  `id` bigint(20) not null auto_increment comment '主键',
  `sensor_id` bigint(20) not null comment '传感器ID',
  `msg` varchar(1000) default null comment '传感器发送过来的消息',
  `time` datetime default null comment '该信息发送过来的时刻',
  primary key (`id`),
  foreign key (`sensor_id`) references `sensor`(`id`)
)ENGINE = InnoDB DEFAULT charset = utf8;


create table `user` (
  `id` bigint(20) not null auto_increment comment '主键',
  `name` varchar(20) default 'admin' comment '用户名',
  `pwd` varchar(20) default 'admin' comment '密码',
  primary key (`id`)
)ENGINE = InnoDB DEFAULT charset=utf8;


insert into `user`() values ();
insert into `user`(`name`,`pwd`) values ('user','123456');

insert into `sensor_state` values ('offline');
insert into `sensor_state` values ('online');

insert into `sensor_type` values ('thunder');
insert into `sensor_type` values ('temperature');
insert into `sensor_type` values ('humidity');
insert into `sensor_type` values ('pressure');

insert into `sensor_config`(`create_user`,`create_time`) values ('admin',now());

insert into `config_detail`(`id`,`type`,`num`) values ('1','thunder',2);
insert into `config_detail`(`id`,`type`,`num`) values ('1','temperature',2);
insert into `config_detail`(`id`,`type`,`num`) values ('1','humidity',2);
insert into `config_detail`(`id`,`type`,`num`) values ('1','pressure',2);

insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'thunder','65521',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'thunder','65522',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'humidity','65523',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'humidity','65524',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'temperature','65525',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'temperature','65526',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'pressure','65527',now());
insert into `sensor`(`config_id`,`type`,`port`,`time`) values (1,'pressure','65528',now());


select * from sensor_config sc ,sensor s where  sc.id = s.config_id and s.type = 'thunder';