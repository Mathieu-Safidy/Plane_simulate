document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.querySelector("tbody");
    const filterForm = document.querySelector("#filterForm");

    // Charger tous les composants au début
    function loadAllComposants() {
        fetch('/composants/findAll')
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = "";
                if (data.length > 0) {
                    data.forEach(composant => {
                        const row = `<tr>
                                        <td>${composant.ordinateur}</td>
                                        <td>${composant.idClient}</td>
                                        <td>${composant.nomClient}</td>
                                        <td>${composant.nomComposant}</td>
                                        <td>${composant.modeleComposant}</td>
                                        <td>${composant.prixVente}</td>
                                        <td>${composant.prixAchat}</td>
                                        <td>${composant.etatComposant}</td>
                                        <td>${composant.typeComposant}</td>
                                        <td>${composant.service}</td>
                                     </tr>`;
                        tableBody.innerHTML += row;
                    });
                } else {
                    tableBody.innerHTML = `<tr>
                                            <td colspan="10" style="text-align: center;">Aucun composant trouvé</td>
                                          </tr>`;
                }
            })
            .catch(error => console.error("Erreur lors du chargement des composants :", error));
    }

    // Charger les options de filtrage (types de composants, ordinateurs, services)
    function loadFilterOptions() {
        fetch('/composants/filterOptions')
            .then(response => response.json())
            .then(data => {
                const typesComposantsSelect = document.querySelector("#typeComposant");
                const typesOrdinateursSelect = document.querySelector("#typeOrdinateur");
                const servicesSelect = document.querySelector("#service");

                // Remplir les sélecteurs avec les options
                populateSelect(typesComposantsSelect, data.typesComposants);
                populateSelect(typesOrdinateursSelect, data.typesOrdinateurs);
                populateSelect(servicesSelect, data.services);
            })
            .catch(error => console.error("Erreur lors du chargement des options de filtrage :", error));
    }

    // Fonction pour remplir un sélecteur avec les options
    function populateSelect(selectElement, options) {
        selectElement.innerHTML = "<option value=''>-- Choisir --</option>";
        options.forEach(option => {
            const optionElement = document.createElement("option");
            optionElement.value = option;
            optionElement.textContent = option;
            selectElement.appendChild(optionElement);
        });
    }

    // Fonction pour appliquer le filtre
    function applyFilter(event) {
        event.preventDefault();

        const typeComposant = document.querySelector("#typeComposant").value;
        const typeOrdinateur = document.querySelector("#typeOrdinateur").value;
        const service = document.querySelector("#service").value;

        let url = '/composants/rechercherLiaisonsParCriteres?';
        if (typeComposant) url += `idTypeComposant=${typeComposant}&`;
        if (typeOrdinateur) url += `idTypeOrdinateur=${typeOrdinateur}&`;
        if (service) url += `idService=${service}&`;

        // Nettoyer l'URL si nécessaire
        url = url.endsWith('&') ? url.slice(0, -1) : url;

        // Rechercher les liaisons avec les critères
        fetch(url)
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = "";
                if (data.length > 0) {
                    data.forEach(composant => {
                        const row = `<tr>
                                        <td>${composant.ordinateur}</td>
                                        <td>${composant.idClient}</td>
                                        <td>${composant.nomClient}</td>
                                        <td>${composant.nomComposant}</td>
                                        <td>${composant.modeleComposant}</td>
                                        <td>${composant.prixVente}</td>
                                        <td>${composant.prixAchat}</td>
                                        <td>${composant.etatComposant}</td>
                                        <td>${composant.typeComposant}</td>
                                        <td>${composant.service}</td>
                                     </tr>`;
                        tableBody.innerHTML += row;
                    });
                } else {
                    tableBody.innerHTML = `<tr>
                                            <td colspan="10" style="text-align: center;">Aucun composant trouvé</td>
                                          </tr>`;
                }
            })
            .catch(error => console.error("Erreur lors de l'application du filtre :", error));
    }

    // Initialiser la page avec les composants et les options de filtrage
    loadAllComposants();
    loadFilterOptions();

    // Appliquer le filtre lors de la soumission du formulaire
    filterForm.addEventListener("submit", applyFilter);
});
