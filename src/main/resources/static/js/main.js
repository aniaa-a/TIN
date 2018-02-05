(function () {
    const navRoutes = {
        home: 'templates/home.html',
        cracow: 'templates/krakow.html',
        zakopane: 'templates/zakopane.html',
        warsaw: 'templates/warszawa.html',
        contact: 'templates/contact.html',
        signIn: 'templates/logowanie.html',
        reservation: 'templates/rezerwacja.html'
    };

    let container = document.getElementById('container'),
        isLogged = false,
        reservation = {},
        userInfo = {};

    bindEvents();
    setNavigation();

    function bindEvents() {
        let signOutBtn = document.getElementById('sign-out'),
            signUpLink = document.getElementById('signUpLink');

        signUpLink.onclick = function () {
            localStorage.setItem('lastLocation', location.hash);
        };

        signOutBtn.onclick = function () {
            isLogged = false;
            userInfo = {};
            setNavigation();
        };

        document.body.onclick = function (event) {
            if (event.target.id === 'reservationButton') {
                reservation.city = event.target.dataset.reservation;
                reservation.price = document.getElementById('tripPrice').dataset.price;
            } else if (event.target.id === 'sign-in-submit') {
                signIn(event);
            }
        };

        window.onhashchange = hashChangeHandler;

        window.dispatchEvent(new Event('hashchange'));
    }

    function replaceHtml(data) {
        container.innerHTML = data;
    }

    function setNavigation() {
        let navAccess = document.querySelectorAll('.nav-access')[0],
            navSignOut = document.querySelectorAll('.sign-out')[0];

        navAccess.hidden = isLogged;
        navSignOut.hidden = !isLogged;
    }

    function hashChangeHandler() {
        if (location.hash === '') {
            location.hash = 'home';
        }

        if (location.hash === '#reservation' && !reservation.city) {
            location.hash = 'home';
            return;
        }

        TIN.Http.ajaxPromise('GET', navRoutes[location.hash.substring(1)])
            .then(function(data) {
                replaceHtml(data.response);

                if (location.hash === '#reservation') {
                    prepareReservation();
                } else {
                    delete reservation.city;
                    delete reservation.price;
                }
            })
            .catch(function(error) {
                console.error('Something went wrong.', error);
            });
    }

    function signIn(event) {
        let loginForm = document.querySelectorAll('.form-login')[0];

        event.preventDefault();

        // todo: jezeli uzytkownik nie istnieje backend musi zwrocic kod bledu lub status (musimy to dogadac)
        TIN.Http.signIn(loginForm.email.value, loginForm.password.value)
            .then(signInSuccessHandler)
            .catch(function (err) {
                alert('Błędny login lub hasło. Spróbuj jescze raz');
                console.error(err);
            })
    }

    function signInSuccessHandler(response) {
        let userData, login = document.querySelectorAll('.user-login')[0];

        try {
            userData = JSON.parse(response.response);
        } catch (err) {
            alert('Błędny login lub hasło. Spróbuj jescze raz');
            console.error(new Error('signIn invalid JSON format response'));
            return;
        }

        login.innerHTML = userData.user.email;
        userInfo = userData.user;
        isLogged = true;
        setNavigation();
        location.hash = '#home';
    }

    function prepareReservation() {
        let reservationForm = document.getElementById('formReservation'),
            reservationPrefix = document.getElementById('reservationPrefix'),
            reservationPrice = document.getElementById('reservationTripPrice');

        reservationPrefix.innerText = reservation.city ? `( ${reservation.city} )` : '';
        reservationPrice.children[0].innerText = reservation.price ? `${reservation.price} zł` : '';

        reservationForm.dateTrip.min = (new Date()).toISOString().split('T')[0];
        reservationForm.city.value = reservation.city;

        if (userInfo.email) {
            reservationForm.mailUser.value = userInfo.email;
        }

        if (userInfo.phone) {
            reservationForm.phoneUser.vale = userInfo.phone;
        }

        reservationForm.onsubmit = function(e) {
            let jsonData;
            const numPeopleValue = this.numPeople.value;
            const data = {
                city: this.city.value,
                phoneUser: this.phoneUser.value,
                mailUser: this.mailUser.value,
                dateTrip: new Date(this.dateTrip.value),
                pricePerPerson: Number(reservation.price),
                numPeople: numPeopleValue
            };

            e.preventDefault();

            try {
                jsonData = JSON.stringify(data);
            } catch (e) {
                console.error('Register data JSON invalid format');
                return;
            }

            alert(jsonData);

            TIN.Http.ajaxPromise('POST', '/reservation/book', jsonData, ['Content-Type', 'application/json'])
                .then(function(data) {
                    console.log(data);
                })
                .catch(function(error){
                    console.error(error);
                });
        }
    }

})();
