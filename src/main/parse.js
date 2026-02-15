this.getLiveMatch = (t,n)=>{
                        1 != this.dataPresent && "series" != n && "upcoming-matches" != n && "finished-matches" != n && this.homeService.getFastLiveMatches().subscribe(n=>{
                            this.liveData = this.getArray(n),
                            this.parseLiveMatch(this.liveData, t, t=>{
                                this.liveData = t,
                                this.seriesWiseMatches = [];
                                for (let n of this.allSeriesNames) {
                                    let t = this.liveData.filter((function(t) {
                                        return t.sfullname === n
                                    }
                                    ));
                                    t.sort((function(t, n) {
                                        return n.f < t.f ? 1 : -1
                                    }
                                    ));
                                    for (let n = 0; n < t.length; n++) {
                                        let e = t[n].mTitle;
                                        if ("" != e && null != e && null != e) {
                                            let c = this.buildGlobalLinkSevice.seriesNameForURL(e.trim().toLowerCase());
                                            c = c.replace(/--/g, "-");
                                            let o = "";
                                            o = t[n].t1Sname < t[n].t2Sname ? t[n].t1Sname + "-vs-" + t[n].t2Sname + "-" + t[n].e + "-" + c : t[n].t2Sname + "-vs-" + t[n].t1Sname + "-" + t[n].e + "-" + c;
                                            let a = o.toLowerCase();
                                            a = a.trim(),
                                            t[n].matchTitle = a,
                                            t[n].mETitle = a.replace(/-/g, " "),
                                            t[n].seriesTitle = c
                                        }
                                    }
                                    let e = {};
                                    if (null != n) {
                                        e.series = n,
                                        e.matches = t,
                                        e.arrayRange = Array(t.length).fill(2),
                                        null != t[0] && (e.order = t[0].f);
                                        let c = 0;
                                        for (let n of t) {
                                            1 == n.s && (this.matchLength++,
                                            c = 1);
                                            let t = n.mTitle;
                                            if ("" != t && null != t && null != t) {
                                                let c = this.buildGlobalLinkSevice.seriesNameForURL(t.trim().toLowerCase());
                                                e.s_fkey = n.q,
                                                e.seriesTitle = c
                                            }
                                            e.live = c,
                                            e.seriesShort = localStorage.getItem(`s_${n.q}_short`),
                                            e.seriesImg = this.seriesImgBaseUrl + n.q + ".png"
                                        }
                                        this.seriesWiseMatches.push(e)
                                    }
                                }
                                this.seriesWiseMatches.sort((function(t, n) {
                                    return n.order < t.order ? 1 : -1
                                }
                                )),
                                this.matchesRange = Array(this.seriesWiseMatches.length).fill(2),
                                this.liveData.sort((function(t, n) {
                                    return n.f < t.f ? 1 : -1
                                }
                                )),
                                this.liveData = t.slice(0, 5);
                                for (let n = 0; n < this.liveData.length; n++) {
                                    let t = this.liveData[n].mTitle;
                                    if ("" != t && null != t && null != t) {
                                        let e = this.buildGlobalLinkSevice.seriesNameForURL(t.trim().toLowerCase())
                                          , c = "";
                                        c = this.liveData[n].t1Sname < this.liveData[n].t2Sname ? this.liveData[n].t1Sname + "-vs-" + this.liveData[n].t2Sname + "-" + this.liveData[n].e + "-" + e : this.liveData[n].t2Sname + "-vs-" + this.liveData[n].t1Sname + "-" + this.liveData[n].e + "-" + e;
                                        let o = c.toLowerCase();
                                        o = o.trim(),
                                        this.liveData[n].matchTitle = o,
                                        this.liveData[n].mETitle = o.replace(/-/g, " "),
                                        this.liveData[n].seriesTitle = e
                                    }
                                }
                                this.desMatchesRange = Array(this.liveData.length).fill(2),
                                this.liveMatchShimmer = !1
                            }
                            )
                        }
                        , t=>{
                            console.log(t)
                        }
                        )
                    }
                    ,
                    this.decalreFunc = (t,n,e)=>{
                        if (1 == e)
                            if (-1 != t.indexOf("!")) {
                                let e = [];
                                null != t && (e = t.split("!"),
                                e = e.slice(1),
                                n.i1 = e[0] + " d")
                            } else
                                -1 == t.indexOf("!") && (n.i1 = t);
                        else if (-1 != t.indexOf("!")) {
                            let e = [];
                            null != t && (e = t.split("!"),
                            e = e.slice(1),
                            n.i2 = e[0] + " d")
                        } else
                            -1 == t.indexOf("!") && (n.i2 = t)
                    }
                    ,
                    this.isSQLKeyObjIsSame = t=>!this.combinedObj || JSON.stringify(this.combinedObj) !== JSON.stringify(t),
                    this.liveHomeParsingMethod = (t,n)=>{
                        const e = this;
                        let c, o, a = [];
                        for (let i of t) {
                            let t, n;
                            // Define match type T20, ODI, Test
                            if (null != i.b && "" != i.b && null != i.c && "" != i.c && "" != i.d && null != i.d) {
                                if (null != i.n) {
                                    if (i.n < 12)
                                        c = Math.floor(parseInt(i.n) / 4),
                                        c = Math.floor(c),
                                        o = parseInt(i.n) % 4,
                                        0 == o ? i.t = "T20" : 1 == o ? i.t = "ODI" : 2 == o ? (i.t = "TEST",
                                        i.dy = 1) : 3 == o && null != i.hb && 1 == i.hb ? i.t = "100B" : 3 == o && (i.t = "T10");
                                    else {
                                        let t = Math.floor(parseInt(i.n) % 12);
                                        c = Math.floor(t / 4),
                                        i.t = "TEST",
                                        i.dy = Math.floor(parseInt(i.n) / 12 + 1)
                                    }
                                    1 == c ? (i.s = 1,
                                    i.s1 = 0) : 0 == c ? (i.s = 0,
                                    i.s1 = 1) : 2 == c && (i.s = 2,
                                    i.s1 = 2),
                                    0 == i.s ? i.stat = "info" : 1 == i.s ? i.stat = "live" : 2 == i.s && (i.stat = "scorecard")
                                }
                                // Define teams playing
                                if (null != i.b && "" != i.b && null != i.c && "" != i.c) {
                                    if (1 == i.d ? (t = i.b,
                                    n = i.c) : 2 == i.d ? (t = i.c,
                                    n = i.b) : 3 == i.d ? (t = i.b,
                                    n = i.c) : 4 == i.d ? (t = i.c,
                                    n = i.b) : 5 == i.d ? (t = i.b,
                                    n = i.c) : 6 == i.d && (t = i.c,
                                    n = i.b),
                                    i.b > i.c) {
                                        let t = i.b;
                                        i.b = i.c,
                                        i.c = t
                                    }
                                    let c = e.img_url_teams + "/Teams/" + n + ".png";
                                    if (i.team1flag = e.img_url_teams + "/Teams/" + t + ".png",
                                    i.team2flag = c,
                                    null != localStorage.getItem("t_" + t + "_cc") && (i.t1cc = localStorage.getItem("t_" + t + "_cc"),
                                    null != i.t1cc && null != i.t1cc && "" != i.t1cc))
                                        if (-1 != i.t1cc.indexOf("-")) {
                                            if (null != i.t1cc) {
                                                let t = i.t1cc.split("-")[0];
                                                i.t1cc = i.t1cc.split("-")[1],
                                                i.fontFColor = "0" == t ? "#ffffff" : "#000000"
                                            }
                                        } else
                                            i.fontFColor = "#ffffff";
                                    if (null != localStorage.getItem("t_" + n + "_cc") && (i.t2cc = localStorage.getItem("t_" + n + "_cc"),
                                    null != i.t2cc && null != i.t2cc && "" != i.t2cc))
                                        if (-1 != i.t2cc.indexOf("-")) {
                                            if (null != i.t2cc) {
                                                let t = i.t2cc.split("-")[0];
                                                i.t2cc = i.t2cc.split("-")[1],
                                                i.fontSColor = "0" == t ? "#ffffff" : "#000000"
                                            }
                                        } else
                                            i.fontSColor = "#ffffff";
                                    i.t1Sname = null === localStorage.getItem("t_" + t + "_short") ? localStorage.getItem("t_" + t + "_name") : localStorage.getItem("t_" + t + "_short"),
                                    i.t2Sname = null === localStorage.getItem("t_" + n + "_short") ? localStorage.getItem("t_" + n + "_name") : localStorage.getItem("t_" + n + "_short"),
                                    i.team1 = 0 == i.s ? null === localStorage.getItem("t_" + t + "_name") ? localStorage.getItem("t_" + t + "_short") : localStorage.getItem("t_" + t + "_name") : null === localStorage.getItem("t_" + t + "_short") ? localStorage.getItem("t_" + t + "_name") : localStorage.getItem("t_" + t + "_short"),
                                    i.team2 = 0 == i.s ? null === localStorage.getItem("t_" + n + "_name") ? localStorage.getItem("t_" + n + "_short") : localStorage.getItem("t_" + n + "_name") : null === localStorage.getItem("t_" + n + "_short") ? localStorage.getItem("t_" + n + "_name") : localStorage.getItem("t_" + n + "_short")
                                }
                                // Define score of each team
                                let r, g, l, s, _, d, p, h, m, b, M;
                                if (0 == i.i && i.d % 2 == 1 ? (i.rate_team = i.t1Sname,
                                i.teamColor = i.t1cc,
                                i.fontColor = i.fontFColor) : 1 == i.i && i.d % 2 == 1 || 0 == i.i && i.d % 2 == 0 ? (i.rate_team = i.t2Sname,
                                i.teamColor = i.t2cc,
                                i.fontColor = i.fontSColor) : 1 == i.i && i.d % 2 == 0 ? (i.rate_team = i.t1Sname,
                                i.teamColor = i.t1cc,
                                i.fontColor = i.fontFColor) : (i.rate_team = i.i,
                                i.fontColor = "ffffff"),
                                1 == i.d)
                                    null != i.j && (r = i.j),
                                    null != i.k && (g = i.k);
                                else if (2 == i.d)
                                    null != i.k && (r = i.k),
                                    null != i.j && (g = i.j);
                                else if (3 == i.d || 4 == i.d) {
                                    if (3 == i.d) {
                                        if (null != i.j && "" != i.j) {
                                            let t = i.j.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 1)
                                        }
                                        if (null != i.k && "" != i.k) {
                                            let t = i.k.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 2)
                                        }
                                    } else if (4 == i.d) {
                                        if (null != i.j && "" != i.j) {
                                            let t = i.j.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 2)
                                        }
                                        if (null != i.k && "" != i.k) {
                                            let t = i.k.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 1)
                                        }
                                    }
                                    null != i.l && (r = i.l),
                                    null != i.m && (g = i.m)
                                } else if (5 == i.d || 6 == i.d) {
                                    if (5 == i.d) {
                                        if (null != i.j && "" != i.j) {
                                            let t = i.j.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 1)
                                        }
                                        if (null != i.k && "" != i.k) {
                                            let t = i.k.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 2)
                                        }
                                    } else if (6 == i.d) {
                                        if (null != i.j && "" != i.j) {
                                            let t = i.j.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 2)
                                        }
                                        if (null != i.k && "" != i.k) {
                                            let t = i.k.split("(")[0].replace("/10", "");
                                            e.decalreFunc(t, i, 1)
                                        }
                                    }
                                    null != i.m && (r = i.m),
                                    null != i.l && (g = i.l)
                                }
                                if (null != r && "" != r && (l = r.split("("),
                                null == g && 2 != i.s ? (i.over2 = "Yet to bat",
                                i.hBalls2 = "Yet to bat") : null != g && (s = g.split("("))),
                                null != l && null != r) {
                                    if (l[0])
                                        if (-1 != l[0].indexOf("!")) {
                                            let t = [];
                                            null != l[0] && (t = l[0].split("!"),
                                            t = t.slice(1),
                                            i.score1 = t[0] + " d")
                                        } else
                                            -1 == l[0].indexOf("!") && (i.score1 = l[0]);
                                    if (l[1] && (i.over1 = l[1]),
                                    null != l && null != i.hb && 1 == i.hb) {
                                        let t = l[1].split(".");
                                        t = 5 * parseInt(t[0]) + parseInt(t[1]),
                                        i.hBalls1 = t + "b"
                                    }
                                }
                                if (null != s && null != g) {
                                    if (s[0])
                                        if (-1 != s[0].indexOf("!")) {
                                            let t = [];
                                            null != s[0] && (t = s[0].split("!"),
                                            t = t.slice(1),
                                            i.score2 = t[0] + " d")
                                        } else
                                            -1 == s[0].indexOf("!") && (i.score2 = s[0]);
                                    if (s[1] && (i.over2 = s[1]),
                                    null != s && null != i.hb && 1 == i.hb) {
                                        let t = s[1].split(".");
                                        t = 5 * parseInt(t[0]) + parseInt(t[1]),
                                        i.hBalls2 = t + "b"
                                    }
                                }
                                if (parseInt(i.d) > 2 && (null != i.k && (_ = i.k),
                                null != i.j && (d = i.j),
                                null != _ && "" != _ && null != d && "" != _)) {
                                    if (p = _.split("("),
                                    h = d.split("("),
                                    null != p && null != p[0] && (m = p[0].split("/"),
                                    -1 != m[0].indexOf("!") && null != m)) {
                                        let t = [];
                                        t = m[0].split("!"),
                                        m = t.slice(1)
                                    }
                                    if (null != h && null != h[0] && (b = h[0].split("/"),
                                    -1 != b[0].indexOf("!") && null != b)) {
                                        let t = [];
                                        t = b[0].split("!"),
                                        b = t.slice(1)
                                    }
                                    null != h && null != p && null != p[0] && null != h[0] && (M = parseInt(b[0]) - parseInt(m[0]))
                                }
                                let C = "";
                                if (null != i.a)
                                    if (C = i.a,
                                    -1 != i.a.indexOf("!") && (i.a = i.a.replace("!", "")),
                                    "&" == i.a.substring(0, 1))
                                        e.getMatchTime(i);
                                    else if ("$" == i.a.substring(0, 1)) {
                                        let t = i.a
                                          , n = new Map([["$a", "Innings Break"], ["$b", "Drinks Break"], ["$c", "Lunch Break"], ["$d", "Tea Break"], ["$e", "Break Title"], ["$f", "Rain Delay"], ["$g", "Low Light Delay"], ["$h", "Match Paused"], ["$i", "Cancelled due to rain"], ["$j", "Cancelled due to low lights"], ["$k", "Match Cancelled"], ["$l", "Stumps"], ["$m", "Timeout"], ["$n", "Match Drawn"], ["$o", "Super Over"], ["$p", "Match Tied"], ["$q", "Abandoned"], ["$r", "Rescheduled"], ["$s", "Toss delayed"], ["$t", "Toss delayed due to rain"], ["$u", "Toss delayed due to bad weather"], ["$v", "Toss delayed due to low light"], ["$w", "Toss delayed due to wet outfield"], ["$x", "No Result"]]);
                                        i.a = n.get(t),
                                        "Stumps" == i.a && (i.statText = "Stumps"),
                                        localStorage.removeItem(i.f_key + "_countdown")
                                    } else {
                                        let e, a, g, l, s, _, d, p, h, C;
                                        switch (localStorage.removeItem(i.f_key + "_countdown"),
                                        "" != i.score1 && null != i.score1 && (h = i.score1.split("/")),
                                        "" != i.score2 && null != i.score2 && (C = i.score2.split("/")),
                                        null != C && null != C[0] && null != h[0] && (e = null != i.o ? Number(i.o) - Number(h[0]) - 1 : Number(C[0]) - Number(h[0])),
                                        null != e && (a = e + 1),
                                        null != r && (g = i.over1.split(".")),
                                        null != g && null != g[0] && null != g[1] && (l = null != i.hb && 1 == i.hb ? 5 * Number(g[0]) + Number(g[1]) : 6 * Number(g[0]) + Number(g[1])),
                                        null != i.p ? (s = Number(i.p),
                                        _ = s - l) : (0 == o ? s = 120 : 1 == o ? s = 300 : 3 == o && null != i.hb && 1 == i.hb ? s = 100 : 3 == o && (s = 60),
                                        _ = s - l),
                                        null != h && null != h[1] && (d = 10 - Number(h[1])),
                                        null != i.score2 && null != i.score1 && (p = i.score2 - i.score1 + 1),
                                        i.a) {
                                        case "^0":
                                            i.a = i.team1 + " won the toss and chose to bat";
                                            break;
                                        case "^1":
                                            i.a = i.team1 + " won the toss and chose to bowl";
                                            break;
                                        case "^2":
                                            i.a = i.team2 + " won the toss and chose to bat";
                                            break;
                                        case "^3":
                                            i.a = i.team2 + " won the toss and chose to bowl";
                                            break;
                                        case "a":
                                            i.a = " Innings break";
                                            break;
                                        case "b":
                                            i.a = " Drinks  break";
                                            break;
                                        case "c":
                                            i.a = " Lunch Break";
                                            break;
                                        case "d":
                                            i.a = " Tea Break";
                                            break;
                                        case "e":
                                            i.a = " Break";
                                            break;
                                        case "f":
                                            i.a = " Match paused due to rain";
                                            break;
                                        case "g":
                                            i.a = " Match paused due to low light";
                                            break;
                                        case "h":
                                            i.a = " Match paused";
                                            break;
                                        case "i":
                                            i.a = " Match cancelled due to rai";
                                            break;
                                        case "j":
                                            i.a = " Match cancelled due to low light";
                                            break;
                                        case "k":
                                            i.a = " Match cancelled";
                                            break;
                                        case "l":
                                            i.a = " Stumps",
                                            i.statText = " Stumps";
                                            break;
                                        case "m":
                                            i.a = " Timeout";
                                            break;
                                        case "n":
                                            i.a = " Match Drawn";
                                            break;
                                        case "o":
                                            i.a = " Super Over";
                                            break;
                                        case "p":
                                            i.a = " Match Tied";
                                            break;
                                        case "q":
                                            i.a = " Abandoned";
                                            break;
                                        case "r":
                                            i.a = " Rescheduled";
                                            break;
                                        case "s":
                                            i.a = " Toss delayed";
                                            break;
                                        case "t":
                                            i.a = " Toss delayed due to rain";
                                            break;
                                        case "u":
                                            i.a = " Toss delayed due to bad weather";
                                            break;
                                        case "v":
                                            i.a = " Toss delayed due to low light";
                                            break;
                                        case "w":
                                            i.a = " Toss delayed due to wet outfield";
                                            break;
                                        case "":
                                            if (2 == i.d)
                                                "T10" == i.t || "ODI" == i.t || "T20" == i.t || "100B" == i.t ? 1 == c ? (i.a = i.team1 + " needs " + a + " to win from " + _ + " balls ",
                                                e < 0 ? (i.a = i.team1 + " won by " + d + " wickets",
                                                i.w = t) : 1 == a && (i.a = "Scores Level")) : 2 == c && (1 == a ? i.a = "Match Tied" : e < 0 ? (i.a = i.team1 + " won by " + d + " wickets",
                                                i.w = t) : e >= 0 && (i.a = i.team2 + " won by " + e + " runs",
                                                i.w = n)) : (1 == a ? i.a = "Scores Level" : a > 1 ? i.a = i.team1 + " trail by " + (a - 1) + " runs" : a < 1 && (i.a = i.team1 + " lead by " + (-1 * a + 1) + " runs"),
                                                parseInt(a) - 1 > 200 && 10 - parseInt(d) > 5 && (i.comment2 = i.team1 + " needs " + (parseInt(a) - 201) + " runs to avoid follow on"));
                                            else if (3 == i.d || 4 == i.d)
                                                4 == i.d ? 0 == d ? i.a = i.team2 + " won by an inning and " + (M - parseInt(h[0])) + " runs" : (i.a = M - parseInt(h[0]) == 0 ? "Scores Level" : M - parseInt(h[0]) < 0 ? 2 == c ? "Match Drawn" : i.team1 + " lead by " + -1 * (M - parseInt(h[0])) + " runs" : i.team1 + " trail by " + (M - parseInt(h[0])) + " runs",
                                                d < 4 && (i.comment2 = i.team2 + " need " + d + " wickets to win")) : 3 == i.d && (i.a = M > 0 ? i.team1 + " lead by " + (M + parseInt(h[0])) + " runs" : M < 0 ? -1 * M - parseInt(h[0]) == 0 ? 0 == d ? i.team2 + " won by an inning" : "Scores Level" : -1 * M - parseInt(h[0]) < 0 ? 2 == c ? "Match Drawn" : i.team1 + " lead by " + -1 * (-1 * M - parseInt(h[0])) + " runs" : 0 == d ? i.team2 + " won by an inning and " + (-1 * M - parseInt(h[0])) + " runs" : i.team1 + " trail by " + (-1 * M - parseInt(h[0])) + " runs" : "Scores Level");
                                            else if (5 == i.d || 6 == i.d)
                                                if (5 == i.d) {
                                                    let e = parseInt(b[0]) + parseInt(h[0])
                                                      , c = parseInt(C[0]) + parseInt(m[0]);
                                                    c - e < 0 ? (i.a = i.team1 + " won by " + d + " wickets ",
                                                    i.w = t) : 0 == d ? c - e > 0 ? (i.a = i.team2 + " won by " + (c - e) + " runs",
                                                    i.w = n) : c - e == 0 && (i.a = "Match Drawn") : (c - e >= 0 ? i.a = i.team1 + " needs " + (c - e + 1) + " runs to win" : c - e < 0 && (i.a = i.team1 + " won by " + d + " wickets",
                                                    i.w = t),
                                                    d < 5 && (i.comment2 = i.team2 + " needs " + d + " wickets to win"))
                                                } else if (6 == i.d) {
                                                    let e = parseInt(b[0]) + parseInt(C[0])
                                                      , c = parseInt(h[0]) + parseInt(m[0]);
                                                    i.a = i.team1 + " needs " + (e - c + 1) + " runs to win",
                                                    0 == d ? e - c == 0 ? i.a = "Match Drawn" : (i.a = i.team2 + " won by " + (e - c) + " runs",
                                                    i.w = n) : e - c >= 0 ? (i.a = i.team1 + " needs " + (e - c + 1) + " runs to win",
                                                    d < 5 && (i.comment2 = i.team2 + " needs " + d + " wickets to win")) : e - c < 0 && (i.a = i.team1 + " won by " + d + " wickets",
                                                    i.w = t)
                                                }
                                        }
                                    }
                                null != i.q && "" != i.q && null != i.q && (i.q = i.q.replace("^", ""),
                                null != i.e && "" != i.e && (i.matchNo = parseInt(i.e),
                                i.e = e.buildGlobalLinkSevice.getMatchNo(i.e, i.q, i.t))),
                                null != i.q && "" != i.q && (i.q = i.q.replace("^", ""),
                                null === localStorage.getItem("s_" + i.q + "_short") ? (i.sname = localStorage.getItem("s_" + i.q + "_name"),
                                i.mTitle = localStorage.getItem("s_" + i.q + "_name")) : (i.sname = localStorage.getItem("s_" + i.q + "_short"),
                                i.sfullname = localStorage.getItem("s_" + i.q + "_name"),
                                i.mTitle = localStorage.getItem("s_" + i.q + "_name"))),
                                null != i.v && "" != i.v && null != localStorage.getItem("v_" + i.v + "_name") && (i.vname = localStorage.getItem("v_" + i.v + "_name")),
                                null != C && "&" == C.substring(0, 1) && 0 == i.s && (i.a = localStorage.getItem(i.f_key + "_countdown")),
                                a.push(i)
                            }
                            if (2 === i.s && null != i.mm) {
                                let t = i.mm.split(/[(/.)]+/);
                                i.momPlayerName = localStorage.getItem("p_" + t[0] + "_name")
                            }
                        }
                        e.getSeriesName(a),
                        a.reverse(),
                        n(a)
                    }
                    ,
                    this.getMatchTime = t=>{
                        let n = Number(this.getNum(t.a.substring(1)))
                          , e = new Date(1e3 * n * 60)
                          , c = e.getDate()
                          , o = (new Date).getDate()
                          , a = e.getTime();
                        if (!window.isServer && (setInterval((function() {
                            var n = (new Date).getTime()
                              , e = a - n
                              , i = Math.floor(e / 864e5)
                              , r = Math.floor(e % 864e5 / 36e5);
                            r = r < 10 ? "0" + r : r;
                            var g = Math.floor(e % 36e5 / 6e4);
                            g = g < 10 ? "0" + g : g;
                            var l = Math.floor(e % 6e4 / 1e3);
                            i < 1 && c == o && (t.a = r >= 1 ? r + "h : " + g + "m " : g + "m : " + l + "s ",
                            localStorage.setItem(t.f_key + "_countdown", t.a))
                        }
                        ), 1e3),
                        c != o)) {
                            const n = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
                            t.liveDays = 1;
                            let c = e;
                            var i = c.getHours()
                              , r = c.getMinutes()
                              , g = i >= 12 ? "PM" : "AM";
                            t.time = (i = (i = (i %= 12) || 12) < 10 ? "0" + i : i) + ":" + (r = r < 10 ? "0" + r : r) + " " + g,
                            t.matchDay = e.getDate() + " " + n[e.getMonth()]
                        }
                    }
                    ,
                    this.viewAllMatch = ()=>{
                        this.iconRotate = !this.iconRotate
                    }
                }