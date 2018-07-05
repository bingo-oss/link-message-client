using System.Collections.Generic;
using Link.Message.Client.utils;
using Newtonsoft.Json;

namespace Link.Message.Client.content.complex.linear
{
	public class GridContent : IStringOrObjectSerializable
	{
		protected internal IList<Column> columns = new List<Column>();
		protected internal IList<string[]> datas = new List<string[]>();
		protected internal int columnCount;

		public GridContent(int columnCount)
		{
			this.columnCount = columnCount;
		}

        [JsonProperty("column")]
		public virtual IList<Column> Columns
		{
			get
			{
				return columns;
			}
		}

        [JsonProperty("data")]
		public virtual IList<string[]> Datas
		{
			get
			{
				return datas;
			}
		}

        [JsonIgnore]
		public virtual int ColumnCount
		{
			get
			{
				return columnCount;
			}
		}

		public virtual void AddColumn(Column column)
		{
			Guard.GuardReqiredObject(column, "column must be set value.");

			if (columns.Count == columnCount)
			{
				Guard.ThrowIllegalArgumentException("grid columns size already equal column count.");
			}

			columns.Add(column);
		}

		public virtual void AddData(string[] data)
		{
			Guard.GuardReqiredArray(data, "data must be set value.");

			if (data.Length != columnCount)
			{
				Guard.ThrowIllegalArgumentException("data array's size must equal column count.");
			}

			datas.Add(data);
		}
	}
}