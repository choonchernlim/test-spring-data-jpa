DROP SCHEMA IF EXISTS dbo;
CREATE SCHEMA dbo;

CREATE TABLE dbo.project (
  projectId  INT IDENTITY (1, 1) NOT NULL
    CONSTRAINT pk_project_projectId PRIMARY KEY,

  name       VARCHAR(100)        NOT NULL,
  totalUsers INT                 NOT NULL,
  created    DATE                NOT NULL,

  CONSTRAINT uq_project_name
  UNIQUE (name)
);