import json
import numpy as np
import pandas as pd

from datetime import date, timedelta

# TODO Add publish data
def generate_json(dataframe):
    for index, row in dataframe.iterrows():
        data = {
            "temperature": row["temperature"],
            "humidity": row["humidity"],
            "pm2.5": row["pm25"],
        }
        json_dump = json.dumps(data)
        print(json_dump)


def main(timeslot, basetmp):

    sdate = date(1997, 3, 22)  # start date
    edate = date(2020, 4, 9)  # end date

    index = pd.date_range(sdate, edate - timedelta(days=1), freq="H")

    time = np.sin(np.arange(0, timeslot, 1))
    pm25 = np.random.normal(90, 15, timeslot)
    humid = np.random.normal(50, 15, timeslot)

    # Add seasonal data
    max_temp = 1
    seasonal = np.arange(0, max_temp, max_temp / timeslot)
    seasonal_pm = np.arange(0, 10, 10 / timeslot)
    temp_flux = np.random.rand(len(time)) * (3)

    temp = temp_flux + basetmp + seasonal
    pm25 = pm25 + seasonal_pm

    date_index = index[: len(temp)]

    df = pd.DataFrame(date_index, columns=["timestamp"])
    df["temperature"] = pd.Series(temp, index=df.index)
    df["humidity"] = pd.Series(humid, index=df.index)
    df["pm25"] = pd.Series(pm25, index=df.index)

    generate_json(df)


if __name__ == "__main__":

    # Number of data points
    timeslot = 200000
    basetemp = 30

    main(timeslot, basetemp)
