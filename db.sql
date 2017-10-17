create table User
(
	uid int auto_increment
		primary key,
	name varchar(32) not null,
	password varchar(32) not null,
	avatar varchar(50) null,
	email varchar(320) null,
	phone varchar(11) null,
	gender int(2) default '0' not null,
	token varchar(32) not null,
	constraint user_token_uindex
		unique (token)
)
;


