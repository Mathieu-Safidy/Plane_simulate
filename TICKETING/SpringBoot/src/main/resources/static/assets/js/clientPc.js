// Gérer la sélection d'un client
document.getElementById('client').addEventListener('change', function () {
    const clientSelect = this;
    const ordinateurSelect = document.getElementById('ordinateur');
    const hiddenOrdiInput = document.getElementById('hiddenOrdiId');

    // Réinitialiser les options du select des ordinateurs
    ordinateurSelect.innerHTML = '<option value="" disabled selected>Choisir un ordinateur</option>';
    hiddenOrdiInput.value = ""; // Réinitialiser la valeur du champ caché

    // Récupérer les ordinateurs associés au client sélectionné
    const clientId = clientSelect.value;
    const clientOption = clientSelect.querySelector(`option[value="${clientId}"]`);

    // Récupérer les ordinateurs associés au client à partir de l'attribut 'data-ordinateurs'
    const ordinateurs = JSON.parse(clientOption.getAttribute('data-ordinateurs'));

    // Ajouter dynamiquement les options des ordinateurs
    if (ordinateurs.length > 0) {
        ordinateurSelect.disabled = false; // Activer le select des ordinateurs
        ordinateurs.forEach(function (ordi) {
            const option = document.createElement('option');
            option.value = ordi.idOrdinateur; // L'ID de l'ordinateur
            option.textContent = ordi.libelle; // Libellé de l'ordinateur
            ordinateurSelect.appendChild(option);
        });
    } else {
        ordinateurSelect.disabled = true; // Désactiver le select si aucun ordinateur
    }
});

// Gérer la sélection d'un ordinateur
document.getElementById('ordinateur').addEventListener('change', function () {
    const hiddenOrdiInput = document.getElementById('hiddenOrdiId');
    hiddenOrdiInput.value = this.value; // Mettre à jour la valeur du champ caché avec l'ID de l'ordinateur sélectionné
});
