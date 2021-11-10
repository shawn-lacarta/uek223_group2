insert into public.authority (id, name)
values  ('9f0ce6d8-4219-11ec-81d3-0242ac130003', 'READ'),
        ('a6952b0e-4219-11ec-81d3-0242ac130003', 'DELETE'),
        ('b971a5cc-4219-11ec-81d3-0242ac130003', 'UPDATE'),
        ('d280cd04-4219-11ec-81d3-0242ac130003', 'CREATE');
insert into public.role (id, name)
values  ('2e510c1e-4217-11ec-81d3-0242ac130003', 'DEFAULT'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', 'ADMIN');	
insert into public.role_authorities (role_id, authority_id)
values  ('2e510c1e-4217-11ec-81d3-0242ac130003', '9f0ce6d8-4219-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', 'a6952b0e-4219-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', 'b971a5cc-4219-11ec-81d3-0242ac130003'),
        ('8f72e074-4219-11ec-81d3-0242ac130003', 'd280cd04-4219-11ec-81d3-0242ac130003');
insert into public.users (id, email, password, username)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', 'admin@email', '123', 'admin'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', 'default@email', 'bond', 'james');
insert into public.users_roles (user_id, role_id)
values  ('2aa70872-421a-11ec-81d3-0242ac130003', '8f72e074-4219-11ec-81d3-0242ac130003'),
        ('415ab3e8-421a-11ec-81d3-0242ac130003', '2e510c1e-4217-11ec-81d3-0242ac130003');		
	