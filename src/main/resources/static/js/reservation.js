class Reservation {
    constructor(selector) {
        const self = this;

        this.form = document.querySelector(selector);
        this.trip = {};

        this.loadDataEvent = new AppEvent(this);

        this.form.addEventListener('submit', function(e) {
            e.preventDefault();
            self.submitReservation();
        });
    }

    loadData(tripId) {
        const url = `trip/getById?id=${tripId}`;

        fetch(url)
            .then(response => {
                if (response.status === 200) {
                    return Promise.resolve(response);
                } else {
                    return Promise.reject(new Error(response.statusText));
                }
            })
            .then(response => response.json())
            .then(data => this.setTrip(data))
            .catch(error => alert(error));
    }

    setTrip(trip) {
        this.trip = trip;
        this.loadDataEvent.notify(trip);
    }

    submitReservation() {
        const url = '/reservation/book';
        const data = {
            city: this.form.city.value,
            dateTrip: this.form.dateTrip.value,
            mailUser: this.form.mailUser.value,
            numPeople: Number(this.form.numPeople.value),
            pricePerPerson: Number(this.trip.pricePerPerson)
        };

        fetch(url, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {'Content-Type': 'application/json'},
        })
            .then(response => Model.checkStatus(response, 201))
            .then(() => {
                alert('Rezerwacja z\u0142o\u017Cona!');
                location.hash = '/home';
            })
            .catch(error => alert(error));
    }

    updateView(user) {
        const priceInfo = document.querySelector('#reservationTripPrice').children[0];

        this.form.mailUser.value = user.email;
        this.form.dateTrip.min = (new Date()).toISOString().split('T')[0];
        this.form.city.value = this.trip.city;
        priceInfo.innerText = `${this.trip.pricePerPerson} zł`;

        this.form.mailUser.disabled = true;
        this.form.city.disabled = true;

        if (user.phone) {
            this.form.phoneUser.value = user.phone;
            this.form.phoneUser.disabled = true;
        }
    }
}
