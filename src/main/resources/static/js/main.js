(function () {
    const model = new Model();
    const app = new App('#container', model);
    const router = new Router(app);

    app.addComponent({
        name: 'home',
        templateUrl: './templates/home.html',
        view() {
            return 'home';
        }
    });
    app.addComponent({
        name: 'cracow',
        templateUrl: './templates/cracow.html',
        controller: tripController
    });
    app.addComponent({
        name: 'warsaw',
        templateUrl: './templates/warsaw.html',
        controller: tripController
    });
    app.addComponent({
        name: 'zakopane',
        templateUrl: './templates/zakopane.html',
        controller: tripController
    });
    app.addComponent({
        name: 'reservation',
        templateUrl: './templates/reservation.html',
        controller: reservationController
    });
    app.addComponent({
        name: 'contact',
        templateUrl: './templates/contact.html',
        view() {
            return 'contact';
        }
    });
    app.addComponent({
        name: 'login',
        templateUrl: './templates/login.html',
        controller: loginController
    });
    app.addComponent({
        name: 'register',
        templateUrl: './templates/register.html',
        controller: registerController
    });

    router.addRoute('home', '^#/home$');
    router.addRoute('cracow', '^#/cracow$');
    router.addRoute('warsaw', '^#/warsaw$');
    router.addRoute('zakopane', '^#/zakopane$');
    router.addRoute('reservation', '^#/reservation$', true);
    router.addRoute('contact', '^#/contact$');
    router.addRoute('login', '^#/login$');
    router.addRoute('register', '^#/register$');


    model.checkIfLogged();

    model.logInEvent.attach(setNavigation);
    model.registeredEvent.attach(function() {
        alert('Zarejestrowa\u0142e\u015B si\u0119!');
        location.hash = '/home';
    });
    model.reservationEvent.attach(function() {
        alert('Rezerwacja z\u0142o\u017Cona!');
        location.hash = '/home';
    });

    setNavigation(model.isLogged);
    window.dispatchEvent(new Event('hashchange'));

    function tripController() {
        const reservationBtn = document.querySelector('#reservationButton');
        const reservationPrice = document.querySelector('#tripPrice');

        reservationBtn.addEventListener('click', function () {
            model.setTrip({
                city: this.dataset.reservation,
                price: reservationPrice.dataset.price
            });
        });
    }

    function reservationController() {
        const form = document.querySelector('#formReservation');
        const priceInfo = document.querySelector('#reservationTripPrice').children[0];

        form.city.value = model.currentTrip.city;
        form.dateTrip.min = (new Date()).toISOString().split('T')[0];
        priceInfo.innerText = `${model.currentTrip.price} zł`;
        form.mailUser.value = model.user.email;

        if (model.user.phone) {
            form.phoneUser.value = model.user.phone;
            form.phoneUser.disabled = true;
        }

        form.addEventListener('submit', function (e) {
            e.preventDefault();

            const reservation = {
                city: this.city.value,
                dateTrip: this.dateTrip.value,
                mailUser: this.mailUser.value,
                numPeople: Number(this.numPeople.value),
                pricePerPerson: Number(model.currentTrip.price)
            };

            model.sendReservation(reservation);
        });
    }

    function loginController() {
        const form = document.querySelector('#loginForm');

        form.addEventListener('submit', function (e) {
            e.preventDefault();

            const user = {
                email: this.email.value,
                password: this.password.value
            };

            model.authorizeUser(user);
        });
    }

    function registerController() {
        const form = document.querySelector('#sign-up-form');

        form.addEventListener('submit', function (e) {
            e.preventDefault();

            const email = form['email'].value;
            const password = form['password'].value;
            const passwordRepat = form['password-repeat'].value;
            const classList = form['password-repeat'].parentElement.classList;

            if (password === passwordRepat) {
                classList.remove('hasError');
                model.registerUser({email: email, password: password});
            } else {
                classList.add('hasError');
            }
        });
    }

    function setNavigation(isLogged) {
        const accessContainer = document.querySelector('.nav-access');
        const userLogin = document.querySelector('.user-login');
        const logoutContainer = document.querySelector('.sign-out');

        accessContainer.hidden = isLogged;
        logoutContainer.hidden = !isLogged;

        if (isLogged) {
            userLogin.innerHTML = model.user.email;
            location.hash = '/home';
        } else {
            userLogin.innerHTML = '';
        }
    }
})();
