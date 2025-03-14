
(function () {
    'use strict';
    const forms = document.querySelectorAll('.requires-validation');    
    Array.from(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
			
            if (!form.checkValidity() || !isValid) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
})();