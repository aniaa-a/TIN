(function () {
    const model = new Model();
    const app = new App('#container', model);
    const router = new Router(app, model);
    const logOutBtn = document.querySelector('#sign-out');

    app.addComponent({
        name: 'home',
        templateUrl: './templates/home.html'
    });
    app.addComponent({
        name: 'trips',
        templateUrl: './templates/trips.html',
        controller: tripsController
    });
    app.addComponent({
        name: 'trip',
        templateUrl: './templates/trip.html',
        controller: tripController
    });
    app.addComponent({
        name: 'reservation',
        templateUrl: './templates/reservation.html',
        controller: reservationController
    });
    app.addComponent({
        name: 'contact',
        templateUrl: './templates/contact.html'
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
    app.addComponent({
        name: 'addTrip',
        templateUrl: './templates/addTrip.html',
        controller: addTripController
    });
    app.addComponent({
        name: 'userReservations',
        templateUrl: './templates/userReservations.html',
        controller: userReservationsController
    });

    router.addRoute('home', '^#/home$');
    router.addRoute('trips', '^#/trips$');
    router.addRoute('trip', '^#/trip/([0-9]*)$');
    router.addRoute('reservation', '^#/reservation/([0-9]*)$', {scope: 'login'});
    router.addRoute('contact', '^#/contact$');
    router.addRoute('userReservations', '^#/userReservations$', {scope: 'login'});
    router.addRoute('login', '^#/login$');
    router.addRoute('register', '^#/register$');
    router.addRoute('addTrip', '^#/addTrip', {scope: 'admin'});

    logOutBtn.addEventListener('click', () => model.logOut());

    model.checkIfLogged();
    model.authorizationEvent.attach(setNavigation);

    window.dispatchEvent(new Event('hashchange'));

    function tripsController() {
        const trips = new Trips('#tripForm');

        trips.loadTrips();
        trips.tripsLoaded.attach(() => {
            trips.updateView();
        });

        trips.getForm().addEventListener('submit', function(e){
            e.preventDefault();

            // przechodzimy do widoku konkretnej wycieczki o id wybranym w select (this.trips.value)
            location.hash = `/trip/${this.trips.value}`;
        });
    }

    function tripController() {
        const trip = new Trip('.trip');

        trip.loadData(router.getParam());
        trip.loadDataEvent.attach(function() {
            trip.updateView();
        });
    }

    function reservationController() {
        const reservation = new Reservation('#formReservation');

        reservation.loadData(router.getParam());
        reservation.loadDataEvent.attach(function() {
            reservation.updateView(model.getUser());
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

    function addTripController() {
        new AddTrip('#addTripFrom');
    }

    function userReservationsController() {
        const userReservations = new UserReservations('#userReservations');

        userReservations.loadReservations(model.getUser().idUser);
        userReservations.loadReservationsEvent.attach(() => userReservations.renderReservations());
    }

    function setNavigation(auhorizationInfo) {
        const accessContainer = document.querySelector('.nav-access');
        const userLogin = document.querySelector('.user-login');
        const logoutContainer = document.querySelector('.sign-out');
        const adminLink = document.querySelector('#adminLink');
        const addTrip = document.querySelector('#addTrip');
        const userReservations = document.querySelector('#userReservationsLink');

        accessContainer.hidden = auhorizationInfo.isLogged;
        logoutContainer.hidden = !auhorizationInfo.isLogged;
        userReservations.hidden = !auhorizationInfo.isLogged;
        adminLink.hidden = !auhorizationInfo.isAdmin;
        addTrip.hidden = !auhorizationInfo.isAdmin;

        if (auhorizationInfo.isLogged) {
            userLogin.innerHTML = model.user.email;
            location.hash = '/home';
        } else {
            userLogin.innerHTML = '';
        }
    }
})();
