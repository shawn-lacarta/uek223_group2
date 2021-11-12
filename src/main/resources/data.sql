insert into public.role (id, name)
values  ('8f72e074-4219-11ec-81d3-0242ac130003', 'OFFICE_WORKER'),
        ('2e510c1e-4217-11ec-81d3-0242ac130003', 'APPRENTICE'),
        ('83f97786-422c-11ec-81d3-0242ac130003', 'INSTRUCTOR');
insert into public.authority (id, name)
values  ('a6952b0e-4219-11ec-81d3-0242ac130003', 'DELETE'),
        ('d280cd04-4219-11ec-81d3-0242ac130003', 'CREATE'),
        ('b971a5cc-4219-11ec-81d3-0242ac130003', 'UPDATE_OWN'),
        ('9f0ce6d8-4219-11ec-81d3-0242ac130003', 'READ_OWN'),
        ('56c5fc8a-422c-11ec-81d3-0242ac130003', 'READ_ALL'),
        ('66262a24-422c-11ec-81d3-0242ac130003', 'UPDATE_ALL');
insert into public.users (id, email, password, username)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', 'instructor@noseryoung.ch', 'LuWid', 'Luca'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', 'nuwera@noseryoung.ch', 'NuMoh', 'Nuwera'),
        ('ad717b9a-422c-11ec-81d3-0242ac130003', 'romy@email.ch', 'RoImh', 'Romy'),
        ('9f8357f6-4407-11ec-81d3-0242ac130003', 'shawn@noseryoung.ch', 'ShLac', 'Shawn'),
        ('9b20c52c-4407-11ec-81d3-0242ac130003', 'matijas@noseryoung.ch', 'MaPol', 'Matijas'),
        ('9e228b98-4407-11ec-81d3-0242ac130003', 'gianluca@email.ch', 'GiDaf', 'Gianluca');
insert into public.users_roles (user_id, role_id)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', '83f97786-422c-11ec-81d3-0242ac130003'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', '2e510c1e-4217-11ec-81d3-0242ac130003'),
        ('ad717b9a-422c-11ec-81d3-0242ac130003', '8f72e074-4219-11ec-81d3-0242ac130003'),
        ('9f8357f6-4407-11ec-81d3-0242ac130003', '2e510c1e-4217-11ec-81d3-0242ac130003'),
        ('9b20c52c-4407-11ec-81d3-0242ac130003', '2e510c1e-4217-11ec-81d3-0242ac130003'),
        ('9e228b98-4407-11ec-81d3-0242ac130003', '83f97786-422c-11ec-81d3-0242ac130003');
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
values  ('d8cf057a-4239-11ec-81d3-0242ac130003', 'NOSER', '31-12-2003', 'Swiss', '0761213141', '415ab3e8-421a-11ec-81d3-0242ac130003'),
        ('5b9b7994-4306-11ec-81d3-0242ac130003', 'NOSER YOUNG', '31-12-2003', 'GERMAN', '0761213141', '2aa70872-421a-11ec-81d3-0242ac130003');