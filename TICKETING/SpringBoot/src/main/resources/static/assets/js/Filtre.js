document.addEventListener("DOMContentLoaded", function () {
    const idTypeComposantSelect = document.getElementById("idTypeComposant");
    const idTypeOrdinateurSelect = document.getElementById("idTypeOrdinateur");
    const idServiceSelect = document.getElementById("idService");
    const dateRetourInput = document.getElementById("dateRetour");  // Ajout de la référence à l'input dateRetour
    const tableBody = document.querySelector("tbody");

    // Charger les options des sélections
    function loadFilterOptions() {
        fetch("/filterOptions") // Endpoint pour récupérer les options
            .then(response => response.json())
            .then(data => {
                console.log("Données reçues : ", data); // Vérifiez les données reçues dans la console
                fillSelect(idTypeComposantSelect, data.typesComposants || []);
                fillSelect(idTypeOrdinateurSelect, data.typesOrdinateurs || []);
                fillSelect(idServiceSelect, data.services || []);
            })
            .catch(error => console.error("Erreur lors du chargement des filtres :", error));
    }

    function fillSelect(selectElement, options) {
        // Réinitialise la liste avec une option par défaut
        selectElement.innerHTML = `<option value="">-- Choisir --</option>`;
    
        // Ajoute les options à la liste
        options.forEach(option => {
            const opt = document.createElement("option");
            opt.value = option.id; // Utilise 'id' comme valeur
            opt.textContent = option.libelle; // Utilise 'libelle' comme texte affiché
            selectElement.appendChild(opt);
        });
    }

    // Charger toutes les liaisons au démarrage
    function loadAllLiaisons() {
        const url = "/rechercherLiaisonsParCriteres";
    
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur réseau : " + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log("Toutes les liaisons :", data);
                const tableBody = document.getElementById("tableBody");
                tableBody.innerHTML = "";
    
                if (data.length > 0) {
                    data.forEach(liaison => {
                        const row = `<tr>
                            <td>${liaison.libelleOrdinateur}</td>
                            <td>${liaison.typeOrdinateur}</td>
                            <td>${liaison.idClient}</td>
                            <td>${liaison.nomClient}</td>
                            <td>${liaison.nomComposant}</td>
                            <td>${liaison.idModel}</td>
                            <td>${liaison.modelComposant}</td>
                            <td>${liaison.prixVente}</td>
                            <td>${liaison.prixAchat}</td>
                            <td>${liaison.etatComposant}</td>
                            <td>${liaison.typeComposant}</td>
                            <td>${liaison.serviceClient}</td>
                            <td>${liaison.dateRetour}</td>
                        </tr>`;
                        tableBody.innerHTML += row;
                    });
                } else {
                    tableBody.innerHTML = `<tr>
                        <td colspan="12" style="text-align: center;">Aucune liaison trouvée</td>
                    </tr>`;
                }
            })
            .catch(error => console.error("Erreur lors du chargement des liaisons :", error));
    }
    
    // Rechercher les liaisons en fonction des critères sélectionnés
    function rechercherLiaisons() {
        const idTypeComposant = idTypeComposantSelect.value || ""; 
        const idTypeOrdinateur = idTypeOrdinateurSelect.value || "";
        const idService = idServiceSelect.value || "";
        const dateRetour = dateRetourInput.value || ""; // Récupérer la valeur de dateRetour
    
        console.log("idTypeComposant:", idTypeComposant);
        console.log("idTypeOrdinateur:", idTypeOrdinateur);
        console.log("idService:", idService);
        console.log("dateRetour:", dateRetour); // Vérifier la date
    
        // Construire l'URL de manière sûre avec tous les critères
        const url = `/rechercherLiaisonsParCriteres?idTypeComposant=${encodeURIComponent(idTypeComposant)}&idTypeOrdinateur=${encodeURIComponent(idTypeOrdinateur)}&idService=${encodeURIComponent(idService)}&dateRetour=${encodeURIComponent(dateRetour)}`;
    
        console.log("URL construite :", url);
    
        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                tableBody.innerHTML = ""; // Réinitialiser le tableau
    
                if (data.length > 0) {
                    data.forEach(liaison => {
                        const row = `<tr>
                            <td>${liaison.libelleOrdinateur}</td>
                            <td>${liaison.typeOrdinateur}</td>
                            <td>${liaison.idClient}</td>
                            <td>${liaison.nomClient}</td>
                            <td>${liaison.nomComposant}</td>
                            <td>${liaison.idModel}</td>
                            <td>${liaison.modelComposant}</td>
                            <td>${liaison.prixVente}</td>
                            <td>${liaison.prixAchat}</td>
                            <td>${liaison.etatComposant}</td>
                            <td>${liaison.typeComposant}</td>
                            <td>${liaison.serviceClient}</td>
                            <td>${liaison.dateRetour}</td>
                        </tr>`;
                        tableBody.innerHTML += row;
                    });
                } else {
                    tableBody.innerHTML = `<tr>
                        <td colspan="10" style="text-align: center;">Aucun composant trouvé</td>
                    </tr>`;
                }
            })
            .catch(error => console.error("Erreur lors de la recherche :", error));
    }

    // Charger les options et toutes les données au démarrage
    loadFilterOptions();
    loadAllLiaisons(); // Charger toutes les données au démarrage

    // Ajouter des événements pour appliquer le filtre lorsqu'un champ change
    idTypeComposantSelect.addEventListener("change", rechercherLiaisons);
    idTypeOrdinateurSelect.addEventListener("change", rechercherLiaisons);
    idServiceSelect.addEventListener("change", rechercherLiaisons);
    dateRetourInput.addEventListener("change", rechercherLiaisons); // Ajout de l'événement pour dateRetour
});
