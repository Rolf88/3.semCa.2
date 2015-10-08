INSERT INTO cityinfo (`CITY`, `ZIP`) VALUES ('København Ø', '2200');
SET @CityId = LAST_INSERT_ID();
INSERT INTO address (`STREET`, `ADDITIONALINFO`, `CITY_ID`) VALUES ('Nørregade 2', '2 th', @CityId);
SET @AddressId = LAST_INSERT_ID();
INSERT INTO address (`STREET`, `ADDITIONALINFO`, `CITY_ID`) VALUES ('Nørregade 3', '2 th', @CityId);
SET @Address2Id = LAST_INSERT_ID();


INSERT INTO cityinfo (`CITY`, `ZIP`) VALUES ('København V', '2300');
SET @Cit32Id = LAST_INSERT_ID();
INSERT INTO address (`STREET`, `ADDITIONALINFO`, `CITY_ID`) VALUES ('Hawaiivej 3', '2 th', @City2Id);
SET @Address3Id = LAST_INSERT_ID();


INSERT INTO infoentity (`DTYPE`, `EMAIL`, `ADDRESS_ID`) VALUES ('Person', 'hahaha@ha.dk', @AddressId);
SET @PersonId = LAST_INSERT_ID();
INSERT INTO Person (Id, firstName, lastName) VALUES (@PersonId, 'Kim', 'Larsen');
INSERT INTO Hobby (`NAME`, `DESCRIPTION`) VALUES ('Fishing', 'Likes to fish');
SET @FishingHobbyId = LAST_INSERT_ID();
INSERT INTO person_hobby (`persons_ID`, `hobbies_ID`) VALUES (@PersonId, @FishingHobbyId);


INSERT INTO  infoentity (`DTYPE`,`EMAIL`,`ADDRESS_ID`) VALUES ('Company', null, @Address2Id);
SET @CompanyId = LAST_INSERT_ID();
INSERT INTO company (Id, `NAME`, `DESCRIPTION`, `CVR`, `MARKETVALUE`, `NUMEMPLOYEES`) VALUES (@CompanyId, 'MyCompany', 'MyCompany test description', 123456789, 234, 4);


INSERT INTO infoentity (`DTYPE`, `EMAIL`, `ADDRESS_ID`) VALUES ('Person', 'hovhov@hov.dk', @AddressId);
SET @PersonId = LAST_INSERT_ID();
INSERT INTO Person (Id, firstName, lastName) VALUES (@PersonId, 'Lukasz', 'Mogensen');


INSERT INTO infoentity (`DTYPE`, `EMAIL`, `ADDRESS_ID`) VALUES ('Person', 'hejhej@hej.dk', @Address3Id);
SET @PersonId = LAST_INSERT_ID();
INSERT INTO Person (Id, firstName, lastName) VALUES (@PersonId, 'Brian', 'Sandberg');
