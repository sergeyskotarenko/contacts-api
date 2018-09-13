drop table if exists contacts;

create table contacts (
  id   integer auto_increment primary key,
  name varchar not null
);