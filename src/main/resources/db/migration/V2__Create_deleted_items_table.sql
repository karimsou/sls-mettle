CREATE table deleted_items
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
