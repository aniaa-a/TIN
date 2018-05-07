class AdminModel {
    constructor() {
        this.getDataEvent = new AppEvent(this);
        this.authorizationEvent = new AppEvent(this);

        this.reservations = [];
        this.cars = [];
        this.drivers = [];
        this.currentEdit = null;
    }

    getData() {
        const reservations = this.getReservations();
        const drivers = this.getDrivers();
        const cars = this.getCars();

        Promise.all([reservations, drivers, cars])
            .then(() => this.getDataEvent.notify())
            .catch(error => alert(error.error));
    }

    checkIfLogged() {
        const url = '/tin/user/isLogged';

        fetch(url, {credentials: 'same-origin'})
            .then(response => {
                if (response.status === 200) {
                    return Promise.resolve(response);
                } else {
                    return Promise.reject(new Error(response.statusText));
                }
            })
            .then(response => response.json())
            .then(data => {
                // jezeli jest zalogowany i ma role admin to go wpuszczamy
                if (data.user && data.user.role === 'admin') {
                    this.authorizationEvent.notify();
                } else {
                    // jezeli nie jest zalogowany albo nie ma admina to wraca na strone glowna
                    window.location = window.location.origin;
                }
            })
            .catch((error) => {
                alert(error);
                // jezeli wystapil blad to go rowniez cofamy
                window.location = window.location.origin;
            });
    }

    getReservations() {
        const url = '/tin/reservation/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                this.reservations = data;
            });
    }

    getDrivers() {
        const url = '/tin/driver/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => this.drivers = data.listCarDriver);
    }

    getCars() {
        const url = '/tin/car/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => this.cars = data.carList);
    }

    findReservationById(id) {
        return this.reservations.filter(reservation => ('' + reservation.idReservation) === id)[0];
    }

    deleteReservation(id) {
        const url = `/tin/reservation/delete?id=${id}`;

        fetch(url, {method: 'DELETE'})
            .then(response => AdminModel.checkStatus(response, 200))
            .then(() => this.getReservations())
            .then(() => this.getDataEvent.notify())
            .catch(error => alert(error.error));
    }

    updateReservation(body) {
        const url = '/tin/admin/addCarDriver';

        fetch(url, {method: 'POST', body: body, headers: {'Content-Type': 'application/json'}})
            .then(response => AdminModel.checkStatus(response, 200))
            .then(() => this.getReservations())
            .then(() => this.getDataEvent.notify())
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
