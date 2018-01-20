(function() {
    let signUpForm = document.getElementById('sign-up-form'),
        emailInput = signUpForm.email,
        passwordInput = signUpForm.password,
        passwordRepeatInput = signUpForm['password-repeat'],
        errorClassName = 'hasError';

    function validateForm() {
        let emailValid = validateEmail(),
            passwordValid = validatePassword(),
            passwordRepeatValid = validatePasswordRepeat();

        return emailValid && passwordValid && passwordRepeatValid;
    }

    signUpForm.onsubmit = function(event) {
        let isFormValid = validateForm(),
            data = {};

        event.preventDefault();

        if (isFormValid) {
            data.email = this.email.value;
            data.password = this.password.value;

            sendData(data).then(function() {
                let lastLocation = localStorage.getItem('lastLocation') || '';

                /* cofamy do miejsca, z ktorego przyszedl uzytkownik lub do strony glownej
                 * jezeli takie miejsce nie istnieje */
                location.replace('/' + lastLocation);

                // todo: ustawic sesje i zalogowac uzytkownika
            }).catch(function(reject) {
                console.error(reject);
                alert('Coś poszło nie tak...\nSpróbuj ponownie później');
            });
        }
    };

    function sendData(data) {
        return new Promise(function(resolve, reject) {
            let xhr = new XMLHttpRequest(),
                jsonData;

            if (typeof data === 'object') {
                try {
                    jsonData = JSON.stringify(data);
                } catch (e) {
                    reject(new Error('Register data JSON invalid format'));
                    return;
                }
            }

            xhr.onload = function() {
                if (this.status === 201) {
                    resolve(this);
                } else {
                    reject(this);
                }
            };

            xhr.open('POST', '/user/register', true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(jsonData);
        });
    }

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
