document.addEventListener('DOMContentLoaded', function() {
    const reparerButton = document.getElementById('serviceButton');
    const remplacerButton = document.getElementById('remplacerButton');
    const form = document.getElementById('formDiagnostique');
    
    reparerButton.addEventListener('click', function() {
        // Définir l'action du formulaire vers le contrôleur de réparation
        form.action = '/Service';  // Chemin vers le contrôleur pour réparer
        form.submit();  // Soumettre le formulaire
    });

    remplacerButton.addEventListener('click', function() {
        // Définir l'action du formulaire vers le contrôleur de remplacement
        form.action = '/Remplacer';  // Chemin vers le contrôleur pour remplacer
        form.submit();  // Soumettre le formulaire
    });
});
