CREATE TABLE IF NOT EXISTS INVENTORY (
    id bigint PRIMARY KEY auto_increment,
    created_at timestamp,
    is_already_borrowed BOOLEAN,
    issued_till_date timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS USER (
    id bigint PRIMARY KEY auto_increment,
    created_at timestamp,
    username varchar(255) not null unique,
    first_name varchar(255),
    is_membership_active BOOLEAN,
    last_name varchar(255),
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS BOOK (
    id bigint PRIMARY KEY auto_increment,
    created_at timestamp,
    author_name varchar(255),
    name varchar(255),
    publisher varchar(255),
    updated_at timestamp,
    user_id bigint,
    inventory_id bigint,
    foreign key (user_id) references USER(id),
    foreign key (inventory_id) references INVENTORY(id),
    UNIQUE KEY `booksUniqueKey` (`author_name`,`name`)
);