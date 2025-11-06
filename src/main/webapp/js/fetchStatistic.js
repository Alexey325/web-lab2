const statisticButton = document.getElementById('statistic-button');

statisticButton.addEventListener('click', async function (e) {
    e.preventDefault();

    this.textContent = 'Отправка...';

    try {

        const attempts = points.length;
        let hits = 0;

        for (let i = 0; i < attempts; i++) {
            if (points[i].hit === "ДА") {
                hits += 1
            }
        }

        const statistics = {
            attempts: attempts,
            hits: hits,
            misses: attempts - hits,
            percentage: hits / attempts * 100
        };

        //console.log(statistics)

        const response = await fetch(contextPath + '/main', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(statistics)
        });

        const result = await response.json();

        if (response.ok) {
            alert('Статистика успешно отправлена на почту!');
        } else {
            alert('Ошибка: ' + result.message);
        }

    } catch {
        alert('Не удалось отправить статистику - ' +
            'ДАННЫЕ ОТСУТСВТУЮТ');
        console.error('Ошибка:', error);
    } finally {
        this.textContent = 'Отправить статистику на почту';
    }

});

