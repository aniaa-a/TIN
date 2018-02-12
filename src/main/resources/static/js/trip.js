class Trip {
    constructor(selector) {
        this.element = document.querySelector(selector);
        this.trip = {};

        this.loadDataEvent = new AppEvent(this);
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

    updateView() {
        const header = this.element.querySelector('#tripHeader');
        const price = this.element.querySelector('#tripPrice');
        const content = this.element.querySelector('#tripContent');
        const link = this.element.querySelector('#reservationLink');

        header.innerHTML = this.trip.city;
        price.innerHTML = `Cena za osob\u0119: ${this.trip.pricePerPerson} z\u0142`;
        content.innerHTML = this.trip.content;
        link.href = `#/reservation/${this.trip.tripId}`;
    }
}
