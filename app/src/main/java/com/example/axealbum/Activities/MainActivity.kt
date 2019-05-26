package com.example.axealbum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.YearAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Data.Memory
import com.example.axealbum.Data.Year
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDb : DataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = DataBase.getInstance(this)

        deleteAll()

        val years = ArrayList<Year>()
        val landmarks = ArrayList<Landmark>()
        val memories = ArrayList<Memory>()

        years.add(Year("2018", R.drawable.img_20180913_202537))
        years.add(Year("2019", R.drawable.img_20190329_195156))

        landmarks.add(getLandmark("Abril", R.drawable.img_20180421_200858, "2018"))
        landmarks.add(getLandmark("Mayo", R.drawable.img_20180526_190747, "2018"))
        landmarks.add(getLandmark("Junio", R.drawable.img_20180624_144401, "2018"))
        landmarks.add(getLandmark("Julio", R.drawable.img_20180728_123749, "2018"))
        landmarks.add(getLandmark("Agosto", R.drawable.img_20180807_203106, "2018"))
        landmarks.add(getLandmark("Septiembre", R.drawable.img_20180913_202537, "2018"))
        landmarks.add(getLandmark("Octubre", R.drawable.img_20181002_195438, "2018"))
        landmarks.add(getLandmark("Noviembre", R.drawable.img_20181102_135456_1, "2018"))
        landmarks.add(getLandmark("Diciembre", R.drawable.img_20181201_164605, "2018"))
        landmarks.add(getLandmark("Enero", R.drawable.img_20190101_000340, "2019"))
        landmarks.add(getLandmark("Febrero", R.drawable.img_20190214_074331, "2019"))
        landmarks.add(getLandmark("Marzo", R.drawable.img_20190303_124133_1, "2019"))
        landmarks.add(getLandmark("Abril", R.drawable.img_20190418_151338, "2019"))
        landmarks.add(getLandmark("Mayo", R.drawable.img_20180913_202537, "2019"))

        memories.add(getMemory("Primera cita :3", R.drawable.img_20180421_200858, "Que decir de nuestra primera cita :33 solo pude encontrar esta fotito :'( pero fue un momento muy lindo conocerteee <3. Recuerdo que estaba todo tímido jajajaja \uD83D\uDE48 y no hable muchito xddd, pero igual para mi fue un momento que nunca olvidaré :3", "Abril", "2018"))
        memories.add(getMemory("Primera sangrada :'(", R.drawable.img_20180422_011400, "En la nochecita cuando fui a la casa de mis amigos xddd y me salió sangre :'(. Quizás seguia emocionado de conocerte? :33 jajajaja pensaba que te había contado que sangre xdddd.", "Abril", "2018"))
        memories.add(getMemory("Esperando a mi anechi :3", R.drawable.img_20180428_170904, "Nuestra segunda cita :3. Esperando a mi anechi pensando ya en el rico pinkberry que se avecinaba xddd aunque acabo siendo un banana split igual de rico <3", "Abril", "2018"))
        memories.add(getMemory("Banana split :3", R.drawable.img_20180428_175128, "Ya con nuestro rico banana split que me invitaste <3. Nuestra segunda cita ya me solté un poco más creo jajajaja :3, recuerdo que estabamos sentaditos al centro y habían varias familias alrededor xddd y que me mencionaste que seguro a mis hermanos les gustaría ese lugar :3", "Abril", "2018"))
        memories.add(getMemory("Paseito por las rotondas xd", R.drawable.img_20180428_200014, "Después de nuestro delicioso banana split fuimos a caminar por las rotondas de por ahi :3, habían varios grupos tocando creo xdd. Esta foto es de un grupo de chica que tocaron recuerdo algo como rock o metal jajajaja, también me acuerdo que habían viejitos bailando en el grupo anterior xdddd y nos reimos un poco de eso :3 jajaja", "Abril", "2018"))
        memories.add(getMemory("Tercera cita <3", R.drawable.img_20180505_144504, "Solo pude encontrar esta fotito :'c. Nuestra tercera cita que nos encontramos en brasil con ejercito :3 y ese dia habia una marcha creo, algo relacionado con mi hijo no te metas o algo asi xdddd. Ese día fue cuando fuimos a buscar los crepes/salchipapas creo y estaba cerrado y acabamos comiendo menu :33 jajaja y habia un traficaso en sucre cuando ibamos a mi casa a que te asustes con las peliciulitas :33 no lo pienses, no lo digas xddd", "Mayo", "2018"))
        memories.add(getMemory("Cuarta cita, pinkberry :3", R.drawable.img_20180512_165458, "Por fin comimos nuestro pinkberry :33. Ese día creo fue el que me equivoqueee y me fui al ovalo guiterrez jajajaja, en vez de irme al ovalo de miraflores xdd :'c no conocia muy bien u.u. Me apuré e igual nos vimos prontito :3 y devoramos bien rico ese dia <3", "Mayo", "2018"))
        memories.add(getMemory("Caminatas :3", R.drawable.img_20180512_183528, "Después de devorar nos fuimos a caminar me acuerdo :3 caminamos hasta llegar cerca al mar y de ahi nos fuimos de freeeeeeeeeente jajajaja hasta el fondaso :3, me gustó mucho nuestra caminata :3 aunque terminamos muertos los 2 creo xddd", "Mayo", "2018"))
        memories.add(getMemory("Eligiendo nueva camisita xdd", R.drawable.img_20180514_190252, "A los pocos días nomas me pase de mi trabajo a plaza un día y me puse a buscar una nueva camisa para impresionarte :3 jajajaja <3, recuerdo que te pregunte cual te parecía mejor y me dijiste que la otra xdddd pero cuando leí tu mensaje ya había comprado :'c jajaja", "Mayo", "2018"))
        memories.add(getMemory("Piano bar <3", R.drawable.img_20180526_190747, "No encontré una fotito de nuestra cita anterior :'c, la semana pasada habíamos ido a Doña Julia :3 y nuestra caminata por Magdalena viendo a esa gente que se iba volando como parapenti xddd, y una señorita me agarro de la manito recuerdo :3 jajaja <3<3. Y este día fuimos a comer nuestro salchipapon con vinito a pino bar <3. Me encantoooo estuvo bien rico :3 y hablamos de muchas cositass, salimos tarde y nos agarramos de la manito nuevamente <3. Todo fue muy lindo ese día, me recontra encanto :33.", "Mayo", "2018"))
        memories.add(getMemory("", R.drawable.img_20180528_184000, "", "Mayo", "2018"))
        memories.add(getMemory("", R.drawable.img_20180603_191555, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180610_143903, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180610_143907, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180620_220328, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180624_144401, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180624_144911, "", "Junio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180701_162222, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180702_223858, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180703_002035, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180708_144728, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180715_185154, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180715_185302, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180718_204732, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180721_232841, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180727_104117, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180727_152207, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180728_123749, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180728_133754_1, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180728_152053, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180728_152550, "", "Julio", "2018"))
        memories.add(getMemory("", R.drawable.img_20180802_082400, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180803_125612, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180807_203106, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180824_082846, "", "Agosto", "2018"))
        memories.add(getMemory("", R.drawable.img_20180901_175627, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180902_142312, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180904_225900, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180913_202334, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180913_202537, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180915_235512, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180929_223648, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20180930_012512, "", "Septiembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181002_195438, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181007_205541, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181013_110324, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181015_175813, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181018_212252_1, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181029, "", "Octubre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_135456_1, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_141027, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_200435, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181102_202912, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181103_091119, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181103_162226, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181109_210126, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_185723, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_185743, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_190139, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_194218, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181122_195844, "", "Noviembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181201_164605, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181201_171015, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181202_180156, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181207_185913, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181215_132216, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181215_132337, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20181229_001551, "", "Diciembre", "2018"))
        memories.add(getMemory("", R.drawable.img_20190101_000340, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190102_192430, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190108_205110, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190113_152651, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190130_192527, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190130_192842_2, "", "Enero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190208_193306, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_074331, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_074802, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_185906, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190214_221541, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190215_191155, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190223_192215, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190226_190454, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190226_191419, "", "Febrero", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_123929, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_124133_1, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190303_131225, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190304_093405, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190329_195156, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190330_144802_1, "", "Marzo", "2019"))
        memories.add(getMemory("", R.drawable.img_20190405_185314, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190416_233420, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190418_151338, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190418_151352, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190421_132743, "", "Abril", "2019"))
        memories.add(getMemory("", R.drawable.img_20190423_191737, "", "Abril", "2019"))

        insertLandmarksInDb(landmarks)
        insertMemoriesInDb(memories)

        val adapter = YearAdapter(years)
        yearsRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager
    }

    fun getEmoji(unicode: Int): String { return String(Character.toChars(unicode)) }

    private fun getLandmark(title: String, image: Int, year: String): Landmark {
        val landmark = Landmark()
        landmark.title = title
        landmark.image = image
        landmark.year = year
        return landmark
    }

    private fun getMemory(title: String, image: Int, comment: String, landmark: String, year: String): Memory {
        val memory = Memory()
        memory.title = title
        memory.image = image
        memory.comment = comment
        memory.landmark = landmark
        memory.year = year
        return memory
    }

    private fun deleteAll() {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()
            mDb?.memoryDao()?.deleteAll()
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertLandmarksInDb(landmarks: List<Landmark>) {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()

            for (i in 0 until landmarks.size) {
                mDb?.landmarkDao()?.insert(landmarks[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertMemoriesInDb(memories: List<Memory>) {

        val task = Runnable {
            mDb?.memoryDao()?.deleteAll()

            for (i in 0 until memories.size) {
                mDb?.memoryDao()?.insert(memories[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }
}
