## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


INSERT INTO commandes (client)
SELECT 'Client ' || generate_series(1, 100000);
INSERT INTO details_commandes (commande_id, produit, quantite, prix)
SELECT floor(random() * 100000) + 1,
'Produit ' || floor(random() * 100),
floor(random() * 10) + 1,
round((random() * 100)::NUMERIC, 2)
FROM generate_series(1, 500000);

SELECT client, COUNT(*), SUM(quantite * prix) AS total_ventes
FROM commandes c JOIN details_commandes d ON c.id = d.commande_id
GROUP BY client
ORDER BY total_ventes DESC;

BEGIN;
INSERT INTO commandes (client) VALUES ('Nouveau Client');
INSERT INTO details_commandes (commande_id, produit, quantite, prix)
VALUES (currval('commandes_id_seq'), 'Produit X', 2, 19.99);
COMMIT;



