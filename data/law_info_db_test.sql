#  建表

create database law_info_db_test;

create table law_0_doc
(
    title        text         null,
    office       text         null,
    publish      date         null,
    expiry       date         null,
    status       text         null,
    type         text         null,
    doc_text     mediumtext   null,
    article_sum  smallint     null,
    doc_category text         null,
    suffix       text         null,
    divided      int          null,
    docURL       text         null,
    full_content longtext     null,
    id           varchar(128) not null
        primary key
);

create table law_1_part
(
    part_seq  text         null,
    part_name mediumtext   null,
    doc_id    varchar(128) null,
    id        varchar(128) not null
        primary key,
    constraint law_1_part_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id)
);

create table law_2_chap
(
    chap_seq  text         null,
    chap_name mediumtext   null,
    part_id   varchar(128) null,
    doc_id    varchar(128) null,
    id        varchar(128) not null
        primary key,
    constraint law_2_chap_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_2_chap_law_1_part_id_fk
        foreign key (part_id) references law_1_part (id)
);

create table law_3_sect
(
    sec_seq   text         null,
    sect_name mediumtext   null,
    chap_id   varchar(128) null,
    part_id   varchar(128) null,
    doc_id    varchar(128) null,
    id        varchar(128) not null
        primary key,
    constraint law_3_sect___fk
        foreign key (part_id) references law_1_part (id),
    constraint law_3_sect_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_3_sect_law_2_chap_id_fk
        foreign key (chap_id) references law_2_chap (id)
);

create table law_4_article
(
    article_seq  text         null,
    article_name mediumtext   null,
    sect_id      varchar(128) null,
    chap_id      varchar(128) null,
    part_id      varchar(128) null,
    doc_id       varchar(128) null,
    id           varchar(128) not null
        primary key,
    constraint law_4_article_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_4_article_law_1_part_id_fk
        foreign key (part_id) references law_1_part (id),
    constraint law_4_article_law_2_chap_id_fk
        foreign key (chap_id) references law_2_chap (id),
    constraint law_4_article_law_3_sect_id_fk
        foreign key (sect_id) references law_3_sect (id)
);

create table law_5_para
(
    para_seq   text         null,
    para_name  mediumtext   null,
    article_id varchar(128) null,
    sect_id    varchar(128) null,
    chap_id    varchar(128) null,
    part_id    varchar(128) null,
    doc_id     varchar(128) null,
    id         varchar(128) not null
        primary key,
    constraint law_5_para_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_5_para_law_1_part_id_fk
        foreign key (part_id) references law_1_part (id),
    constraint law_5_para_law_2_chap_id_fk
        foreign key (chap_id) references law_2_chap (id),
    constraint law_5_para_law_3_sect_id_fk
        foreign key (sect_id) references law_3_sect (id),
    constraint law_5_para_law_4_article_id_fk
        foreign key (article_id) references law_4_article (id)
);

create table law_6_subpara
(
    subpara_seq  text         null,
    subpara_name mediumtext   null,
    para_id      varchar(128) null,
    article_id   varchar(128) null,
    sect_id      varchar(128) null,
    chap_id      varchar(128) null,
    part_id      varchar(128) null,
    doc_id       varchar(128) null,
    id           varchar(128) not null
        primary key,
    constraint law_6_subpara_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_6_subpara_law_1_part_id_fk
        foreign key (part_id) references law_1_part (id),
    constraint law_6_subpara_law_2_chap_id_fk
        foreign key (chap_id) references law_2_chap (id),
    constraint law_6_subpara_law_3_sect_id_fk
        foreign key (sect_id) references law_3_sect (id),
    constraint law_6_subpara_law_4_article_id_fk
        foreign key (article_id) references law_4_article (id),
    constraint law_6_subpara_law_5_para_id_fk
        foreign key (para_id) references law_5_para (id)
);

create table law_7_item
(
    item_seq   text         null,
    item_name  mediumtext   null,
    subpara_id varchar(128) null,
    para_id    varchar(128) null,
    article_id varchar(128) null,
    sect_id    varchar(128) null,
    chap_id    varchar(128) null,
    part_id    varchar(128) null,
    doc_id     varchar(128) null,
    id         varchar(128) not null
        primary key,
    constraint law_7_item_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id),
    constraint law_7_item_law_1_part_id_fk
        foreign key (part_id) references law_1_part (id),
    constraint law_7_item_law_2_chap_id_fk
        foreign key (chap_id) references law_2_chap (id),
    constraint law_7_item_law_3_sect_id_fk
        foreign key (sect_id) references law_3_sect (id),
    constraint law_7_item_law_4_article_id_fk
        foreign key (article_id) references law_4_article (id),
    constraint law_7_item_law_5_para_id_fk
        foreign key (para_id) references law_5_para (id),
    constraint law_7_item_law_6_subpara_id_fk
        foreign key (subpara_id) references law_6_subpara (id)
);

create table law_8_appendix
(
    appendix_seq  text         null,
    appendix_name mediumtext   null,
    doc_id        varchar(128) null,
    id            varchar(128) not null
        primary key,
    constraint law_8_appendix_law_0_doc_id_fk
        foreign key (doc_id) references law_0_doc (id)
);