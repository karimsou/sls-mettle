CREATE table items
(
    id          varchar PRIMARY KEY,
    name        varchar(20),
    description varchar(200),
    type        varchar,
    cost        numeric,
    created_at  timestamp,
    updated_at  timestamp,
    deleted_at  timestamp
);

insert into items
values ('26937741-15a2-435b-82b0-39cd0539ed5e', 'Item Name', 'Item Name', 'hockey_pads', 20.00, '2022-03-10 14:46:55',
        '2022-03-10 14:46:55', null)