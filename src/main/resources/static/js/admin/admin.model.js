class AdminModel {
    constructor() {
        this.getDataEvent = new AppEvent(this);

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

    getReservations() {
        const url = '/reservation/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                this.reservations = data;
            });
    }

    getDrivers() {
        const url = '/driver/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => this.drivers = data.listCarDriver);
    }

    getCars() {
        const url = '/car/getAll';

        return fetch(url)
            .then(response => AdminModel.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => this.cars = data.carList);
    }

    findReservationById(id) {
        return this.reservations.filter(reservation => ('' + reservation.idReservation) === id)[0];
    }

    deleteReservation(id) {
        const url = `/reservation/delete?id=${id}`;

        fetch(url, {method: 'DELETE'})
            .then(response => AdminModel.checkStatus(response, 200))
            .then(() => this.getReservations())
            .then(() => this.getDataEvent.notify())
            .catch(error => alert(error.error));
    }

    updateReservation(body) {
        const url = '/admin/addCarDriver';

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
