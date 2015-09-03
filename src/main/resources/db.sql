DROP SCHEMA IF EXISTS dbo;
CREATE SCHEMA dbo;

CREATE TABLE dbo.project (
  projectId INT IDENTITY (1, 1) NOT NULL
    CONSTRAINT pk_project_projectId PRIMARY KEY,

  name      VARCHAR(100)        NOT NULL,
  created   DATETIME            NOT NULL,

  CONSTRAINT uq_project_name
  UNIQUE (name)
);

CREATE TABLE dbo.user (
  userId    INT IDENTITY (1, 1) NOT NULL
    CONSTRAINT pk_user_userId PRIMARY KEY,

  projectId INT                 NOT NULL,
  name      VARCHAR(100)        NOT NULL,

  CONSTRAINT fk_user_projectId FOREIGN KEY (projectId)
  REFERENCES dbo.project (projectId)
    ON DELETE CASCADE,

  CONSTRAINT uq_user_projectId_name
  UNIQUE (projectId, name)
);
