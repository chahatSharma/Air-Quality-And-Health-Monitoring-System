$(function () {
alert(dataSet1);
    var chart = new Highcharts.Chart({
        chart: {
            type: 'column',
            renderTo: 'chartcontainer',
            inverted: false
        },
        title: {
            text: 'Indoor Outdoor AQI Hourly Comparison'
        },
//        subtitle: {
//            text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
//        },
        xAxis: {
            //categories: ['Indoor AQI', 'Outdoor AQI'],
            min: 0,
            tickInterval: 1,
            title: {
                text: 'Vital Signs'
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Index',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ''

        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
                name: 'Respiration Rate',
                data: dataSet1}, {
                name: 'Heart Rate',
                data: dataSet2},{
                name: 'Blood Pressure',
                data: dataSet3},{
                name: 'Weight',
                data: dataSet4
            }]
    });
});
