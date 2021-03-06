INSERT INTO company (CNPJ, FANTASY_NAME, FILE_PATH)
VALUES ('11111111111111','EMPRESA UM LTDA','/home/aderlian/Downloads/XML/xmlUm/');

INSERT INTO company (CNPJ, FANTASY_NAME, FILE_PATH)
VALUES ('22222222222222','EMPRESA DOIS LTDA','/home/aderlian/Downloads/XML/xmlDois/');

INSERT INTO company (CNPJ, FANTASY_NAME, FILE_PATH)
VALUES ('333333333333333','EMPRESA TRES LTDA','/home/aderlian/Downloads/XML/xmlTres/');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE UM, NUMERO 1','11111111111111','FORNECEDOR UM LTDA','SC');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE DOIS, NUMERO 2','222222222222222','FORNECEDOR DOIS LTDA','SP');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE TRES, NUMERO 3','333333333333333','FORNECEDOR TRES LTDA','RS');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE QUATRO, NUMERO 4','444444444444444','FORNECEDOR QUATRO LTDA','PR');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE CINCO, NUMERO 5','555555555555555','FORNECEDOR CINCO LTDA','SC');

INSERT INTO provider (CITY, CNPJ, NAME, STATE)
VALUES ('CIDADE SEIS, NUMERO 6','66666666666666','FORNECEDOR SEIS LTDA','MG');

INSERT INTO danfe (AMOUNT, CREATION_DATE, ISSUANCE_DATE, KEY_NFE, NUMBER_NFE, ID_COMPANY, ID_PROVIDER)
VALUES (53.72, TO_DATE('2020-10-03','YYYY-MM-DD'), TO_DATE('2018-09-15','YYYY-MM-DD'),
'111111111111111111111111111111111111111111111', 111, 1, 6);

INSERT INTO danfe (AMOUNT, CREATION_DATE, ISSUANCE_DATE, KEY_NFE, NUMBER_NFE, ID_COMPANY, ID_PROVIDER)
VALUES (53.72, TO_DATE('2020-10-03','YYYY-MM-DD'), TO_DATE('2020-10-01','YYYY-MM-DD'),
'33333333333333333333333333333333333333333333', 130, 1, 6);

INSERT INTO danfe (AMOUNT, CREATION_DATE, ISSUANCE_DATE, KEY_NFE, NUMBER_NFE, ID_COMPANY, ID_PROVIDER)
VALUES (53.72, TO_DATE('2020-10-03','YYYY-MM-DD'), TO_DATE('2019-09-25','YYYY-MM-DD'),
'22222222222222222222222222222222222222222222', 120, 2, 6);