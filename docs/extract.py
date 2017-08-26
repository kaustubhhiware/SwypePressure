from prettytable import PrettyTable
from collections import OrderedDict
import sys
#
# Extract useful features and display as time
#
def main():
    with open(sys.argv[1]) as f:
        s = f.read()

    s = s.split('\n')
    num_parameters = int(sys.argv[2]) # 1 for pressure only, 2 for untimed, 3 for all

    for i in range(len(s)):
        s[i] = s[i].split(' ')

    p =OrderedDict()
    output = "<html><body><table>"    
    output += "<tr><td>Time</td><td>Pressure</td>"
    if num_parameters == 1:
        table = PrettyTable(['Time', 'Pressure'])
    elif num_parameters == 2:
        output += "<td>Velocity</td>"
        table = PrettyTable(['Time', 'Pressure', 'Velocity'])
    else:
        output += "<td>Velocity</td><td>Time event</td>"
        table = PrettyTable(['Time', 'Pressure', 'Velocity', 'Timestats'])

    output += "</tr>"
    for i in range(len(s)):
        if s[i][0] == '':
            s.pop(i)
        else:
            l = s[i]
            if l[1] not in p:
                if num_parameters == 1:
                    p[l[1]] = {'pressure':'-'}
                elif num_parameters == 2:
                    p[l[1]] = {'pressure': '-', 'vel': '-'}
                else:
                    p[l[1]] = {'pressure': '-', 'vel': '-', 'time': '-'}

            attr = l[len(l) - 2]
            val = l[len(l) - 1]
            if attr == 'Time:':
                p[l[1]]['time'] = val
            elif attr == 'Pressure:':
                p[l[1]]['pressure'] = val
            else:
                p[l[1]]['vel'] = val

    for each in p:
        row = [each]
        output += "<tr><td>"+each+"</td><td>"+p[each]['pressure']+"</td>"
        row.append(p[each]['pressure'])

        if num_parameters > 1:
            row.append(p[each]['vel'])
            output += "<td>"+p[each]['vel']+"</td>"

            if num_parameters > 2:
                row.append(p[each]['time'])
                output += "<td>"+p[each]['time']+"</td>"

        output += "</tr>"
        table.add_row(row)


    output += "</table></body></html>"
    # print output
    with open("temp.html", "w") as text_file:
        text_file.write("%s" % output)

    print table


if __name__ == '__main__':
    main()