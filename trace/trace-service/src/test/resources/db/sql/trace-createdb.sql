create sequence route_sequence start with 1 increment by 1;

create table routes (
   id_route integer generated by default as sequence route_sequence primary key
  ,is_ready boolean default false not null
  ,time integer default -1
);

create table points (
   id_route integer
  ,id_point integer not null
  ,position integer not null
  ,primary key(id_route, position)
  ,CONSTRAINT fk_rps_id_route FOREIGN KEY (id_route) REFERENCES routes (id_route)
);