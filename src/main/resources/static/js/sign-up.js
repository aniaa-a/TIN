(function() {
    let signUpForm = document.getElementById('sign-up-form'),
        emailInput = document.getElementById('email'),
        passwordInput = document.getElementById('password'),
        passwordRepeatInput = document.getElementById('password-repeat'),
        errorClassName = 'hasError';

    function validateForm() {
        let emailValid = validateEmail(),
            passwordValid = validatePassword(),
            passwordRepeatValid = validatePasswordRepeat();

        return emailValid && passwordValid && passwordRepeatValid;
    }

    signUpForm.onsubmit = function(a) {
        let isFormValid = validateForm();

        a.preventDefault();

        if (isFormValid) {
            // Rejestrujemy uzytkownika
        }
    };

    function validateEmail() {
        let regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
            isValid = regex.test(emailInput.value.toLowerCase()),
            classList = emailInput.parentElement.classList;

        isValid ? classList.remove(errorClassName) : classList.add(errorClassName);
        return isValid;
    }

    function validatePassword() {
        let classList = passwordInput.parentElement.classList,
            isValid = passwordInput.value.length >= 5;

        isValid ? classList.remove(errorClassName) : classList.add(errorClassName);
        return isValid;
    }

    function validatePasswordRepeat() {
        let classList = passwordRepeatInput.parentElement.classList,
            isValid = passwordRepeatInput.value === passwordInput.value;

        isValid ? classList.remove(errorClassName) : classList.add(errorClassName);
        return isValid;
    }

})();