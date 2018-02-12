class AddTrip {
    constructor(selector) {
        const self = this;

        this.form = document.querySelector(selector);
        this.form.addEventListener('submit', function (e) {
            e.preventDefault();
            self.saveTrip();
        });
    }

    saveTrip() {
        const url = 'trip/addTrip';
        const data = {
            city: this.form.city.value,
            departureTime: this.form.departureTime.value,
            arriveTime: this.form.arriveTime.value,
            pricePerPerson: this.form.pricePerPerson.value,
            content: this.form.content.value
        };

        fetch(url, {method: 'POST', body: JSON.stringify(data), headers: {'Content-Type': 'application/json'}})
            .then(response => {
                if (response.status === 201) {
                    alert('Wycieczka dodana!');
                    location.hash = '/home';
                } else {
                    return Promise.reject(response.statusText);
                }
            })
            .catch(error => alert(error));
    }
}
