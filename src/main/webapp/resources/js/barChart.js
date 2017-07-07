$(function () {
    var dataSet1 = [107, 31, 635, 203, 2, 105, 107, 31, 635, 203, 2, 107, 31, 635, 203, 2, 107, 31, 635, 203, 2, 107, 31, 635];
//var dataSet1 = document.getElementById('dataset1').value;

    var dataSet2 = [133, 156, 947, 408, 6, 133, 156, 947, 408, 6, 133, 156, 947, 408, 6, 133, 156, 947, 408, 6, 133, 156, 947, 408];
 //var dataSet2=   document.getElementById('dataset2').value;
//alert(dataSet1);
//var dataSet3 = document.getElementById('dataset3').value;
//var dataSet4 = document.getElementById('dataset4').value;
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
                text: 'Time'
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
                name: 'Indoor AQI',
                data: dataSet1}, {
                name: 'Outdoor AQI',
                data: dataSet2
            }]
    });
});
