    create table bean (
        id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table bean 
        add constraint UK_NAME unique (name);
