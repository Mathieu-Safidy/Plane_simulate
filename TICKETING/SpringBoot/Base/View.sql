CREATE OR REPLACE VIEW infoComposant AS 
    SELECT  
        composant.idComposant , 
        composant.nom , 
        composant.model , 
        TypeComposant.libelle 
    FROM  
        composant  
        LEFT JOIN TypeComposant ON composant.idType = TypeComposant.idType;  


CREATE OR REPLACE VIEW infoComposant as 
SELECT
    composant.idComposant,
    composant.nom,
    TypeComposant.idType , 
    TypeComposant.libelle AS typeComposant , 
    Model.idModel,
    Model.libelle AS model,
    COALESCE(SUM(MvtStockFille.entree) - SUM(MvtStockFille.sortie), 0) AS StockRestantComposant,
    TypeComposant.libelle,
    composant.prixVente,
    composant.prixAchat
FROM
    composant
    LEFT JOIN TypeComposant ON composant.idType = TypeComposant.idType
    LEFT JOIN MvtStockFille ON composant.idComposant = MvtStockFille.idComposant
    LEFT JOIN Model ON Model.idModel = composant.idModel
WHERE etat = 1
GROUP BY
    composant.idComposant,
    composant.nom,
    Model.idModel,
    Model.libelle,
    TypeComposant.idType , 
    TypeComposant.libelle;


CREATE OR REPLACE VIEW VueClientOrdinateurComposants AS
SELECT
    o.idOrdinateur,
    o.Libelle AS LibelleOrdinateur,
    typeOrdi.idType AS idTypeOrdinateur,
    typeOrdi.Libelle AS TypeOrdinateur,
    cli.idClient,
    cli.nom AS NomClient,
    comp.nom AS NomComposant,
    Model.idModel,
    Model.libelle AS ModelComposant,
    comp.prixVente,
    comp.prixAchat,
    comp.etat AS EtatComposant,
    t.idType AS idType,
    t.libelle AS TypeComposant,
    loc.Libelle AS ProblemeComposant,
    ServiceClient.idService,
    ServiceClient.libelle AS ServiceClient , 
    loc.dateRetour AS dateRetour 
FROM
    Ordinateur o
INNER JOIN
    Client cli ON o.idClient = cli.idClient
INNER JOIN
    LiaisonOrdiComposant loc ON o.idOrdinateur = loc.idOrdinateur
INNER JOIN
    Composant comp ON loc.idComposant = comp.idComposant
INNER JOIN
    TypeComposant t ON comp.idType = t.idType
INNER JOIN
    typeOrdi ON typeOrdi.idType = o.idType
INNER JOIN
    ServiceClient ON ServiceClient.idService = loc.idService
INNER JOIN
    Model ON Model.idModel = comp.idModel
ORDER BY
    cli.idClient;




SELECT idOrdinateur, LibelleOrdinateur,
            idTypeOrdinateur, TypeOrdinateur
            idClient, NomClient, 
            NomComposant, idModel, ModelComposant, prixVente, prixAchat, EtatComposant
            idType, TypeComposant, ProblemeComposant, 
            idService, ServiceClient FROM VueClientOrdinateurComposants ; 



CREATE OR REPLACE VIEW VueMoisComposant AS
SELECT 
    mc.idLiaison,
    m.idMois,
    m.libelle AS mois,
    c.nom AS composant,
    c.idModel,
    c.idType,
    c.prixVente,
    c.prixAchat,
    mc.annee
FROM 
    MoisComposant mc
JOIN 
    Mois m ON mc.idMois = m.idMois
JOIN 
    Composant c ON mc.idComposant = c.idComposant;

