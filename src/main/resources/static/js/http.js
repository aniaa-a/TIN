var TIN = TIN || {};

TIN.Http = (function() {
    const endpoints = {
        signIn: '/user/log',
        isLogged: '/user/isLogged'
    };

    function ajaxPromise (method, url, data, header) {
        return new Promise(function(resolve, reject) {
            let xhr = new XMLHttpRequest();

            xhr.onload = function() {
                if (this.status === 200) {
                    resolve(this);
                } else {
                    reject(this);
                }
            };

            xhr.open(method, url, true);
            if (header) {
                xhr.setRequestHeader(header[0], header[1]);
            }
            xhr.send(data ? data : '');
        });
    }

    // todo: nie dziala. Prawdopodobnie robota "po backendzie" :D
    function isLogged(email) {
        let url = `${endpoints.isLogged}?email=${email}`;

        return ajaxPromise('GET', url);
    }

    function signIn(email, password) {
        let url = `${endpoints.signIn}?email=${email}&password=${password}`;

        return ajaxPromise('GET', url)
    }

    return {
        isLogged: isLogged,
        signIn: signIn
    }
})();
