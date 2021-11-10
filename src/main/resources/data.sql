insert into public.role (id, name)
values  ('8f72e074-4219-11ec-81d3-0242ac130003', 'OFFICE_WORKER'),
        ('2e510c1e-4217-11ec-81d3-0242ac130003', 'Apprentice'),
        ('83f97786-422c-11ec-81d3-0242ac130003', 'INSTRUCTOR');
insert into public.authority (id, name)
values  ('a6952b0e-4219-11ec-81d3-0242ac130003', 'DELETE'),
        ('d280cd04-4219-11ec-81d3-0242ac130003', 'CREATE'),
        ('b971a5cc-4219-11ec-81d3-0242ac130003', 'UPDATE_OWN'),
        ('9f0ce6d8-4219-11ec-81d3-0242ac130003', 'READ_OWN'),
        ('56c5fc8a-422c-11ec-81d3-0242ac130003', 'READ_ALL'),
        ('66262a24-422c-11ec-81d3-0242ac130003', 'UPDATE_ALL');
insert into public.users (id, email, password, username)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', 'instructor@email', '123', 'instructor'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', 'apprentice@email', 'bond', 'james'),
        ('ad717b9a-422c-11ec-81d3-0242ac130003', 'office@email.ch', 'work', 'malin');
insert into public.users_roles (user_id, role_id)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', '8f72e074-4219-11ec-81d3-0242ac130003'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', '2e510c1e-4217-11ec-81d3-0242ac130003'),
        ('ad717b9a-422c-11ec-81d3-0242ac130003', '8f72e074-4219-11ec-81d3-0242ac130003');
insert into public.role_authorities (role_id, authority_id)
values  ('8f72e074-4219-11ec-81d3-0242ac130003', 'd280cd04-4219-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', 'd280cd04-4219-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', '9f0ce6d8-4219-11ec-81d3-0242ac130003'),
        ('2e510c1e-4217-11ec-81d3-0242ac130003', '9f0ce6d8-4219-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', '9f0ce6d8-4219-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', '56c5fc8a-422c-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', '56c5fc8a-422c-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', 'b971a5cc-4219-11ec-81d3-0242ac130003'),
        ('2e510c1e-4217-11ec-81d3-0242ac130003', 'b971a5cc-4219-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', 'b971a5cc-4219-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', '66262a24-422c-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', '66262a24-422c-11ec-81d3-0242ac130003'),
        ('83f97786-422c-11ec-81d3-0242ac130003', 'a6952b0e-4219-11ec-81d3-0242ac130003');
insert into public.user_profile (id, address, birth_date, nationality, phone_number, user_id)
values  ('d8cf057a-4239-11ec-81d3-0242ac130003', 'NOSER', '31-12-2003', 'Swiss', '0761213141', '415ab3e8-421a-11ec-81d3-0242ac130003');