# currency-convertor

TO obtain the exchange rate for conversion of currency A to currency B:
http://localhost:8080/api/exchange-rate?baseCode=AUD&targetCode=INR
Response:
{
  "sourceCurrency": "AUD",
  "targetCode": "INR",
  "exchangeRate": "54.417308"
}

TO obtain all the exchange rates for currency A:
http://localhost:8080/api/exchange-rate?baseCode=AUD
Response:
{
  "sourceCurrency": "AUD",
  "targetCode": "all",
  "exchangeRate": "{AED=2.426749, AFN=57.324823, ALL=67.139854, AMD=253.783505, ANG=1.190869, AOA=389.475142, ARS=158.68315, AUD=1.0, AWG=1.19143, AZN=1.123639, BAM=1.206332, BBD=1.322101, BDT=70.845622, BGN=1.200327, BHD=0.249709, BIF=1863.684091, BMD=0.660878, BND=0.893204, BOB=4.563775, BRL=3.313645, BSD=0.66137, BTC=2.5E-5, BTN=54.43943, BWP=9.067997, BYN=1.667287, BZD=1.331644, CAD=0.887799, CDF=1537.096151, CHF=0.598077, CLF=0.019454, CLP=531.6349, CNH=4.684328, CNY=4.676119, COP=2923.863115, CRC=354.646304, CUC=0.660891, CUP=17.012697, CVE=67.694424, CZK=14.527529, DJF=117.043959, DKK=4.570107, DOP=36.089035, DZD=90.071696, EGP=20.40912, ERN=9.909807, ETB=35.902337, EUR=0.613991, FJD=1.477771, FKP=0.527583, GBP=0.527346, GEL=1.721118, GGP=0.527608, GHS=7.412936, GIP=0.527438, GMD=39.356283, GNF=5676.596333, GTQ=5.171315, GYD=139.672561, HKD=5.173627, HNL=16.162286, HRK=4.624058, HTG=92.127436, HUF=227.776574, IDR=9815.121466, ILS=2.474405, IMP=0.527142, INR=54.417308, IQD=861.175285, IRR=27945.991302, ISK=91.984506, JEP=0.527451, JMD=101.626652, JOD=0.469227, JPY=91.788534, KES=91.502259, KGS=57.865413, KHR=2709.078429, KMF=304.796461, KPW=594.595406, KRW=863.078598, KWD=0.203291, KYD=0.550775, KZT=295.014656, LAK=11813.233303, LBP=9912.454264, LKR=193.942784, LRD=111.734562, LSL=13.006141, LYD=3.189994, MAD=6.688696, MDL=11.72392, MGA=2903.956906, MKD=38.092744, MMK=1386.810554, MNT=2324.86912, MOP=5.327644, MRU=22.621686, MUR=30.126385, MVR=10.108742, MWK=674.777737, MXN=11.582327, MYR=3.034269, MZN=42.216348, NAD=13.02845, NGN=304.730475, NIO=24.156517, NOK=7.290153, NPR=87.103535, NZD=1.084306, OMR=0.255207, PAB=0.661177, PEN=2.415564, PGK=2.363097, PHP=36.962591, PKR=187.555712, PLN=2.769916, PYG=4781.30306, QAR=2.397569, RON=3.048052, RSD=71.936907, RUB=53.289473, RWF=742.127283, SAR=2.477795, SBD=5.506704, SCR=9.097784, SDG=397.058389, SEK=7.124171, SGD=0.888658, SHP=0.527482, SLL=11670.590252, SOS=374.409609, SRD=24.991887, SSP=86.057651, STD=15078.937405, STN=15.040536, SVC=5.779023, SYP=1659.932731, SZL=13.002681, THB=22.852622, TJS=7.215223, TMT=2.313033, TND=2.042201, TOP=1.573416, TRY=13.81333, TTD=4.469905, TWD=20.246438, TZS=1562.465012, UAH=24.280461, UGX=2466.023067, USD=0.661384, UYU=25.631436, UZS=7534.84202, VES=17.264191, VND=15506.672118, VUV=78.60469, WST=1.801153, XAF=402.47045, XAG=0.028094, XAU=7.17E-4, XCD=1.78573, XDR=0.494179, XOF=402.470249, XPD=9.26E-4, XPF=73.217678, XPT=0.001135, YER=165.396983, ZAR=12.958826, ZMW=12.835281, ZWL=212.733491}"
}

Get the conversion value from currency A to currency B:
http://localhost:8080/api/convert-currency?amount=100&baseCode=AUD&targetCode=INR
Response:
{
  "sourceCurrency": "AUD",
  "targetCode": "INR",
  "convertedAmount": "5441.73"
}

Get the conversion amount from currency A to list of currencies:
http://localhost:8080/api/convert-currency?amount=100&baseCode=AUD&targetCode=INR,EUR
{
  "sourceCurrency": "AUD",
  "targetCode": "INR,EUR",
  "convertedAmount": "5441.73,61.40"
}
