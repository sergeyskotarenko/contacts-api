drop table if exists contacts;

create table contacts (
  id   serial  not null primary key,
  name varchar not null
);