drop table if exists user_account;
drop table if exists likes;
drop table if exists image;
drop table if exists feed;
drop table if exists comment;


create table user_account
(
    id          bigint auto_increment,
    email       varchar(50)  not null,
    name        varchar(50)  not null,
    password    varchar(50)  not null,
    created_at  datetime     not null,
    created_by  varchar(100) not null,
    modified_at datetime     not null,
    modified_by varchar(100) not null,
    constraint email
        unique (email),
    constraint id
        unique (id)
);

create table likes
(
    id          bigint auto_increment
        primary key,
    feed_id     mediumtext   not null,
    user_id     mediumtext   null,
    created_at  datetime     not null,
    created_by  varchar(100) not null,
    modified_at datetime     null,
    modified_by varchar(100) null,
    constraint id
        unique (id)
);
create table image
(
    image_url   varchar(200) not null,
    id          bigint auto_increment
        primary key,
    feed_id     bigint       null,
    created_at  datetime     not null,
    created_by  varchar(100) not null,
    modified_at datetime     null,
    modified_by varchar(100) null,
    constraint id
        unique (id)
);

create table feed
(
    id          bigint auto_increment
        primary key,
    content     varchar(500) null,
    created_at  datetime     null,
    created_by  varchar(100) null,
    modified_at datetime     null,
    modified_by varchar(100) null,
    constraint id
        unique (id)
);


create table comment
(
    id                bigint auto_increment
        primary key,
    parent_comment_id bigint       null,
    content           varchar(500) not null,
    created_at        datetime     not null,
    created_by        varchar(100) null,
    modified_at       datetime     null,
    modified_by       varchar(100) null,
    feed_id           mediumtext   not null,
    user_id           mediumtext   not null,
    constraint id
        unique (id)
);

