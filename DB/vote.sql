/* DDL */

CREATE TABLE [dbo].[Voter] (
    [voterNo]   INT           IDENTITY (1, 1) NOT NULL,
    [isAdmin]   BIT           NULL,
    [votername] VARCHAR (255) NOT NULL,
    [password]  VARCHAR (255) NULL,
    PRIMARY KEY CLUSTERED ([voterNo] ASC)
);

CREATE TABLE [dbo].[VoteItem] (
    [voteItemNo]   INT           IDENTITY (1, 1) NOT NULL,
    [voteItemName] VARCHAR (255) NOT NULL,
    PRIMARY KEY CLUSTERED ([voteItemNo] ASC)
);

CREATE TABLE [dbo].[VoteRecord] (
    [voteRecordNo] INT IDENTITY (1, 1) NOT NULL,
    [voteItem]     INT NOT NULL,
    [voter]        INT NOT NULL,
    PRIMARY KEY CLUSTERED ([voteRecordNo] ASC),
    CONSTRAINT [FKd8xyawkdly2mubex4ridq7umy] FOREIGN KEY ([voteItem]) REFERENCES [dbo].[VoteItem] ([voteItemNo]),
    CONSTRAINT [FKd9l0y96vh324pqlv3nec9xc19] FOREIGN KEY ([voter]) REFERENCES [dbo].[Voter] ([voterNo])
);

/* DML */

INSERT into VoteItem (voteItemName) VALUES('鍵盤')
INSERT into VoteItem (voteItemName) VALUES('電腦')
INSERT into VoteItem (voteItemName) VALUES('滑鼠')


INSERT into Voter (votername, isAdmin, password) VALUES('Amy' , 0, '1234')
INSERT into Voter (votername, isAdmin, password ) VALUES('Bob' , 0, '1234')
INSERT into Voter (votername, isAdmin, password) VALUES('Ray' , 0, '1234')