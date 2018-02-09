class Model {
    constructor() {
        this.templateLoaded = new AppEvent(this);
        this.authorizationEvent = new AppEvent(this);
        this.reservationEvent = new AppEvent(this);

        this.currentTrip = {};
        this.user = {};
        this.isLogged = false;
    }

    setTrip(trip) {
        this.currentTrip = trip;
    }

    getTemplate(url) {
        fetch(url)
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.text())
            .then(data => this.templateLoaded.notify(data))
            .catch(error => alert(error.error));
    }

    sendReservation(data) {
        const url = '/reservation/book';

        data = JSON.stringify(data);

        fetch(url, {
            method: 'POST',
            body: data,
            headers: {'Content-Type': 'application/json'},
        })
            .then(response => Model.checkStatus(response, 201))
            .then(() => this.reservationEvent.notify())
            .catch(error => alert(error.error));
    }

    authorizeUser(user) {
        const url = `/user/log?email=${user.email}&password=${user.password}`;

        fetch(url, {credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                if (data.user) {
                    this.logIn(data.user);
                } else {
                    throw new Error(data.status);
                }
            })
            .catch(error => alert(error.error));
    }

    logIn(user) {
        this.isLogged = true;
        this.user = user;
        this.authorizationEvent.notify(this.isLogged);
    }

    logOut() {
        const url = '/user/logout';

        fetch(url, {method: 'POST', credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(() => {
                this.isLogged = false;
                this.user = null;
                this.authorizationEvent.notify(this.isLogged);
            })
            .catch(error => alert(error.error));
    }

    checkIfLogged() {
        const url = '/user/isLogged';

        fetch(url, {credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                if (data.status === 'ok') {
                    this.logIn(data.user);
                } else {
                    this.authorizationEvent.notify(this.isLogged);
                }
            })
            .catch(error => {
                this.authorizationEvent.notify(this.isLogged);
                alert(error.error);
            });
    }

    registerUser(user) {
        const url = '/user/register';

        fetch(url, {method: 'POST', body: JSON.stringify(user), headers: {'Content-Type': 'application/json'}})
            .then(response => {
                if (response.status === 201) {
                    this.authorizeUser(user);
                } else if (response.status === 200) {
                    alert('Podany adres email jest ju\u017C zaj\u0119ty')
                } else {
                    return Promise.reject(response.statusText);
                }
            })
            .catch(error => alert(error.error));
    }

    static checkStatus(response, status) {
        if (response.status === status) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(new Error(response.statusText));
        }
    }
}
