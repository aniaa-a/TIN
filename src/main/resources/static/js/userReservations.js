class UserReservations {
    constructor(containerSelector) {
        this.container = document.querySelector(containerSelector);
        this.loadReservationsEvent = new AppEvent(this);
        this.reservations = [];
    }

    loadReservations(id) {
        const url = `reservation/getByUser?id=${id}`;

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
                this.setReservations(data);
                this.loadReservationsEvent.notify(data);
            })
            .catch(error => alert(error));
    }

    setReservations(reservations) {
        this.reservations = reservations;
    }

    renderReservations() {
        const table = document.createElement('table');

        // jezeli nie ma rezerwacji to pomijamy reszte funkcji i dodaje informacje o braku rezerwacji
        if (!this.reservations.length) {
            this.container.innerHTML = '<h2>Nie masz żadnych rezerwacji</h2>';
            return;
        }

        table.id = 'userReservations';
        table.innerHTML = UserReservations.tableHeaderTemplate();

        for (let reservation of this.reservations) {
            table.innerHTML += `
                <tr>
                    <td>${reservation.city}</td>
                    <td>${reservation.dateTrip}</td>
                    <td>${reservation. departureTime}</td>
                    <td>${reservation.arriveTime}</td>
                    <td>${reservation.numPeople}</td>
                    <td>${reservation.pricePerPerson} zł</td>
                </tr>`
        }

        // czyscimy kontener i dodajemy do niego tabele;
        this.container.innerHTML = '';
        this.container.appendChild(table);
    }

    static tableHeaderTemplate() {
        return `
            <tr>
                <th>Miasto</th>
                <th>Data</th>
                <th>Godzina odjazdu</th>
                <th>Godzina przyjazdu</th>
                <th>Ilość osób</th>
                <th>Cena za osobe</th>
            </tr>`
    }
}
